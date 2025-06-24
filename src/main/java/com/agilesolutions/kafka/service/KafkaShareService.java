package com.agilesolutions.kafka.service;

import com.agilesolutions.kafka.model.Share;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaShareService {

    @Autowired
    private final KafkaConsumer<String, Share> consumer;



    public List<Share> getAllShares() {

        consumer.subscribe(Collections.singleton("default"));

        List<Share> shares = new ArrayList<>();

        log.info("Get all Shares");

        ConsumerRecords<String, Share> records = consumer.poll(Duration.ofMillis(100));

        for (ConsumerRecord<String, Share> record : records) {
            shares.add((Share) record.value().get("content"));
            log.info("Message time: " + record.value().get("date_time"));
        }
        consumer.commitAsync();

        return shares;

    }

}
