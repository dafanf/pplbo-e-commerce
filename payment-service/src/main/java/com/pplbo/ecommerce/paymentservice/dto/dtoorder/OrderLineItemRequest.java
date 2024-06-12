package com.pplbo.ecommerce.paymentservice.dto.dtoorder;

public record OrderLineItemRequest(
        int quantity,
        int productId) {
}
