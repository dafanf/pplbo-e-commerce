package com.pplbo.ecommerce.productservice.dto;

public record ProductRequest(String name, Long price, String description, Integer brandId, String image) {
}
