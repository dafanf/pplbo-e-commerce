package com.pplbo.ecommerce.productservice.dto;


public record ProductReviewRequest(Integer rating, String comment, Integer productId) {


}