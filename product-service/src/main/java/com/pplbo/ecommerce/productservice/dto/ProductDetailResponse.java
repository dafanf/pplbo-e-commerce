package com.pplbo.ecommerce.productservice.dto;

import java.util.List;

public record ProductDetailResponse(Integer id, String name, Long price, String description, String brand, String image, List<String> category)  {

}