package com.pplbo.ecommerce.cart.service.dto;

import java.util.List;

import com.pplbo.ecommerce.cart.service.model.Product;

public record CartResponse(Long id, String userID, List<Product> products, Integer totalPrice) {
}