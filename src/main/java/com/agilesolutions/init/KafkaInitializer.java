package com.agilesolutions.init;


import com.agilesolutions.dto.Share;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class KafkaInitializer implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, Share> kafkaTemplate;



    @Override
    public void run(String... args) {

        Share share = Share.newBuilder().setCompany("AAPL").setId(1).setQuantity(100).setUpdated(Instant.now()).build();

        kafkaTemplate.send("default", null, share);

        log.info("Kafka records saved successfully-------");

    }
}
