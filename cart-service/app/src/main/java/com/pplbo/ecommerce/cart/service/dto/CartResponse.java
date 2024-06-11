package com.pplbo.ecommerce.cart.service.dto;

import java.util.List;

import com.pplbo.ecommerce.cart.service.model.Product;

public record CartResponse(Long id, Long userID, List<Product> products, Long totalPrice) {
}