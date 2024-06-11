package com.pplbo.ecommerce.productservice.event.domain;

import com.pplbo.ecommerce.productservice.dto.OrderResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderCreateEvent implements Event {
    private OrderResponse order;

    public OrderCreateEvent(OrderResponse order) {
        this.order = order;
    }

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
        this.order = order;
    }
}
