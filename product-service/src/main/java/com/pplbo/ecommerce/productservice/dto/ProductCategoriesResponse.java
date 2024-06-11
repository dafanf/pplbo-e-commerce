package com.pplbo.ecommerce.productservice.dto;

import java.util.List;

public record ProductCategoriesResponse(Integer productId, List<String> categoryName) {
}
