package com.pplbo.ecommerce.paymentservice.dto.dtoorder;

public record OrderLineItemResponse(
        Long orderLineItemId,
        int quantity,
        int productId) {
}
