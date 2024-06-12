package com.pplbo.ecommerce.productservice.dto.dtoorder;

public record ShippingRequest(
    String shippingName,
    Double shippingPrice,
    String shippingStatus,
    String shipping,
    String shippingAddress
) {}
