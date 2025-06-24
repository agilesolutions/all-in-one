package com.agilesolutions.config;

import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka(
        partitions = 1,
        topics = {"default"},
        controlledShutdown = false,
        brokerProperties = {"listeners=PLAINTEXT://localhost:9502", "port=9502" })
public class KafkaBroker {
}
