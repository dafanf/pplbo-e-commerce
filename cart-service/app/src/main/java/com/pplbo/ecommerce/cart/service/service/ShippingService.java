package com.pplbo.ecommerce.cart.service.service;

import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.dto.ShippingRequest;
import com.pplbo.ecommerce.cart.service.model.Shipping;

@Service
public class ShippingService {
    public Shipping saveShipping(ShippingRequest request) {
        return Shipping.builder().shippingName(request.shippingName()).shippingPrice(10000)
                .shippingAddress(request.shippingAddress()).shippingStatus("Pending").build();
    }
}
