package com.pplbo.ecommerce.paymentservice.dto.dtoorder;

public record ShippingRequest(
        String shippingName,
        Double shippingPrice,
        String shippingStatus,
        String shipping,
        String shippingAddress) {
}
