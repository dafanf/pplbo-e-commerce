package com.pplbo.ecommerce.productservice.dto;

public record ShippingResponse(
        Long shippingId,
        String shippingName,
        Double shippingPrice,
        String shippingStatus,
        String shippingAddress) {
}