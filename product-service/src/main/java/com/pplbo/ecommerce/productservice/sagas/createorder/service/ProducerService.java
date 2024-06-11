package com.pplbo.ecommerce.productservice.sagas.createorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pplbo.ecommerce.productservice.sagas.createorder.event.OrderEvent;

@Service
public class ProducerService {

    private static final String TOPIC = "orderReplyEvent";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendOrderReplyEvent(OrderEvent event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
