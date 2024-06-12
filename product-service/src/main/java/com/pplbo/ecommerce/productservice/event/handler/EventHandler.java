package com.pplbo.ecommerce.productservice.event.handler;

import com.pplbo.ecommerce.productservice.event.domain.Event;

public interface EventHandler<T extends Event> {
    void handleEvent(T event);

    Class<T> getTypeParameterClass();
}