package com.pplbo.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.orderservice.model.Message;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String aggregateType, String aggregateEventType, Object event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            Message message = new Message(aggregateType, aggregateEventType, payload);
            String messageJson = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(aggregateEventType, messageJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
