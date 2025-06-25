package com.agilesolutions.config;

import com.agilesolutions.kafka.model.Share;
import com.agilesolutions.kafka.serdes.AvroDeserializer;
import com.agilesolutions.kafka.serdes.AvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public EmbeddedKafkaBroker broker() {
        EmbeddedKafkaBroker broker = new EmbeddedKafkaKraftBroker(1, 1, "default")
                .brokerProperties(Map.of("listeners","PLAINTEXT://localhost:9092", "port", "9092" ));
        return broker;
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("default").partitions(1).replicas(1).build();
    }


    @Bean
    public ConsumerFactory<String, Share> consumerFactory(EmbeddedKafkaBroker broker) {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, broker.getBrokersAsString());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "default");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
        configProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, Share> producerFactory(EmbeddedKafkaBroker broker) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker.getBrokersAsString());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Share> kafkaTemplate(EmbeddedKafkaBroker broker) {
        var kafkaTemplate = new KafkaTemplate<>(producerFactory(broker));
        kafkaTemplate.setConsumerFactory(consumerFactory(broker));
        kafkaTemplate.setDefaultTopic("default");
        return kafkaTemplate;
    }

    @Bean
    public KafkaConsumer<String, Share> kafkaConsumer(EmbeddedKafkaBroker broker) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker.getBrokersAsString());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "default");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);  //ensures records are properly converted

        return new KafkaConsumer<>(properties);

    }

}
