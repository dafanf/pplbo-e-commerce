package com.pplbo.promotion.service;

import com.pplbo.promotion.event.PromotionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "promotion-events";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(PromotionEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
