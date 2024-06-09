package com.pplbo.ecommerce.cart.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.dto.CartRequest;
import com.pplbo.ecommerce.cart.service.model.Cart;
import com.pplbo.ecommerce.cart.service.model.ProductCart;
import com.pplbo.ecommerce.cart.service.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(CartRequest cartRequest) {
        Cart cart = Cart.builder()
                .userID(cartRequest.userID())
                .products(cartRequest.products())
                .selectedProducts(cartRequest.selectedProducts())
                .totalPrice(cartRequest.totalPrice())
                .build();
        return cart;
    }
}
