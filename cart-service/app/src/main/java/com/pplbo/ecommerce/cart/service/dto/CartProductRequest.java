package com.pplbo.ecommerce.cart.service.dto;

import com.pplbo.ecommerce.cart.service.model.Product;

public record CartProductRequest(Product product, Long totalPrice) {

}
