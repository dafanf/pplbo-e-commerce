package com.pplbo.ecommerce.productservice.event;

import com.pplbo.ecommerce.productservice.model.User;


public class OrderCreatedEvent {
    private User user;

        // Konstruktor default
    public OrderCreatedEvent() {}

    public OrderCreatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
