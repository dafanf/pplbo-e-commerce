package com.pplbo.orderservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import com.pplbo.orderservice.event.domain.OrderCreateEvent;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public NewTopic OrderCreatedEvent() {
        return TopicBuilder.name(OrderCreateEvent.class.getName())
                .partitions(10)
                .replicas(1)
                .build();
    }
}
