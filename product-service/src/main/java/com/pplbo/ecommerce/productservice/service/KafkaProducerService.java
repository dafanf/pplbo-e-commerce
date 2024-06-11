package com.pplbo.ecommerce.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.ecommerce.productservice.event.OrderCreatedEvent;
import com.pplbo.ecommerce.productservice.event.domain.OrderCreateEvent;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "OrderProductEvent";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendUserCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(orderCreatedEvent);
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
