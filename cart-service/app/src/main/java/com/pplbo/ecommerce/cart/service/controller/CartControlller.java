package com.pplbo.ecommerce.cart.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pplbo.ecommerce.cart.service.dto.CartRequest;
import com.pplbo.ecommerce.cart.service.model.Cart;
import com.pplbo.ecommerce.cart.service.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartControlller {
    @Autowired
    private CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createProduct(@RequestBody CartRequest cartRequest) {
        return cartService.createCart(cartRequest);
    }
}
