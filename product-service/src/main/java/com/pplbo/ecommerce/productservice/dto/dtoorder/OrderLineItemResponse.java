package com.pplbo.ecommerce.productservice.dto.dtoorder;

public record OrderLineItemResponse(
    Long orderLineItemId,
    int quantity,
    int productId
) {}
