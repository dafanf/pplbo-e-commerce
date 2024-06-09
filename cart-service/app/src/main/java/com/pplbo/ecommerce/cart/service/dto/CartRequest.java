package com.pplbo.ecommerce.cart.service.dto;

import java.util.List;

import com.pplbo.ecommerce.cart.service.model.ProductCart;

public record CartRequest(String userID, List<ProductCart> products, List<ProductCart> selectedProducts,
        Integer totalPrice) {

}