package com.pplbo.ecommerce.productservice.sagas.createorder.event;

import com.pplbo.ecommerce.productservice.dto.dtoorder.OrderResponse;
import com.pplbo.ecommerce.productservice.model.order.Order;

public class OrderEvent {
    private OrderResponse order;

    public OrderEvent() {
    }
    
    public OrderEvent(OrderResponse order) {
        this.order = order;
    }   

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
        this.order = order;
    }

}
