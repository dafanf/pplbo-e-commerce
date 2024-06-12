package com.pplbo.ecommerce.productservice.dto;

public record OrderLineItemResponse(
        Long orderLineItemId,
        int quantity,
        int productId) {
}
