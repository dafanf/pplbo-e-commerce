package com.pplbo.ecommerce.productservice.dto;

public record ProductResponse(Integer id, String name, Long price, String description, Integer brandId, String image) {
}
