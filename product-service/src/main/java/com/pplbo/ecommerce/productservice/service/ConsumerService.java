package com.pplbo.ecommerce.productservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.ecommerce.productservice.event.domain.OrderCreateEvent;

@Service
public class ConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "coba", groupId = "group_id")
    public void consume(String message) {
        try {
            OrderCreateEvent event = objectMapper.readValue(message, OrderCreateEvent.class);
            handleOrderCreateEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOrderCreateEvent(OrderCreateEvent event) {
        // Process the event
        System.out.println("Handling user created event for user: " + event.getOrder().customer().firstName());
    }
}
