package com.pplbo.ecommerce.cart.service.dto;

public record ProductRequest(Long id, String name, Long price, String description, Integer brandId,
                Integer quantityToBuy) {
}