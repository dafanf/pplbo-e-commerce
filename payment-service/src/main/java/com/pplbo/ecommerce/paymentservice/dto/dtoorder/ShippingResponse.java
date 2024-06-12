package com.pplbo.ecommerce.paymentservice.dto.dtoorder;

public record ShippingResponse(
        Long shippingId,
        String shippingName,
        Double shippingPrice,
        String shippingStatus,
        String shippingAddress) {
}
