package com.pplbo.ecommerce.paymentservice.event;

import com.pplbo.ecommerce.paymentservice.dto.dtoorder.OrderResponse;
import com.pplbo.ecommerce.paymentservice.model.order.Order;

public class OrderPaymentEvent {
    private OrderResponse order;

    public OrderPaymentEvent() {
    }

    public OrderPaymentEvent(OrderResponse order) {
        this.order = order;
    }

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
        this.order = order;
    }

}
