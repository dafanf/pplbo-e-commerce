package com.pplbo.ecommerce.productservice.event;

import com.pplbo.ecommerce.productservice.dto.dtoorder.OrderResponse;
import com.pplbo.ecommerce.productservice.model.order.Order;

public class OrderCreatedEvent {
    private OrderResponse order;

    public OrderCreatedEvent() {
    }
    
    public OrderCreatedEvent(OrderResponse order) {
        this.order = order;
    }   

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
        this.order = order;
    }

}
