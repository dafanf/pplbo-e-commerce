package com.pplbo.ecommerce.productservice.dto;


public record ProductReviewRequest(Integer id,Integer rating, String comment, Integer productId) {


}