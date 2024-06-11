package com.pplbo.ecommerce.productservice.event.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.ecommerce.productservice.event.domain.OrderCreateEvent;

import com.pplbo.ecommerce.productservice.model.Message;

@Configuration
public class EventHandlerConfig {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "com.pplbo.orderservice.event.domain.OrderCreateEvent", groupId = "group_id")
    public void consumeCreateUser(String message) {
        try {
            Message msg = objectMapper.readValue(message, Message.class);
            System.out.println(msg.toString());
            OrderCreateEvent event = objectMapper.readValue(msg.getPayload(), OrderCreateEvent.class);
            System.out.println(event.getOrder().customer().firstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}