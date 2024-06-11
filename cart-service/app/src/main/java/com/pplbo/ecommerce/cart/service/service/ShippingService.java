package com.pplbo.ecommerce.cart.service.service;

import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.dto.ShippingRequest;
import com.pplbo.ecommerce.cart.service.model.Shipping;

public class ShippingService {
    public Shipping saveShipping(ShippingRequest request, Double shippingPrice) {
        return Shipping.builder().shippingName(request.shippingName()).shippingPrice(shippingPrice)
                .shippingAddress(request.shippingAddress()).shippingStatus("Pending").build();
    }
}
