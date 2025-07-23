package com.example.jpa2.kafkaService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "product-topic", groupId = "product-group")
    public void consume(String message) {
        System.out.println("🔔 Получено сообщение из Kafka: " + message);
    }
}
