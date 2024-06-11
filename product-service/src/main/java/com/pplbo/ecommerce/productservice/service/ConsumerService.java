package com.pplbo.ecommerce.productservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pplbo.ecommerce.productservice.event.OrderCreatedEvent;

@Service
public class ConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "coba", groupId = "group_id")
    public void consume(String message) {
        try {
            OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);
            handleOrderCreatedEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOrderCreatedEvent(OrderCreatedEvent event) {
        // Process the event
        System.out.println("Handling user created event for user: " + event.getUser().getName());
    }
}
