package com.pplbo.ecommerce.productservice.dto.dtoorder;

public record OrderLineItemRequest(
    int quantity,
    int productId
) {}
