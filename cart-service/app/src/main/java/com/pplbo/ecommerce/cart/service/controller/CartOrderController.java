package com.pplbo.ecommerce.cart.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pplbo.ecommerce.cart.service.dto.CartRequest;
import com.pplbo.ecommerce.cart.service.dto.ShippingRequest;
import com.pplbo.ecommerce.cart.service.model.Cart;
import com.pplbo.ecommerce.cart.service.model.CartOrder;
import com.pplbo.ecommerce.cart.service.service.CartOrderService;
import com.pplbo.ecommerce.cart.service.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartOrderController {
    @Autowired
    private CartOrderService cartOrderService;

    @PostMapping("/{id}/order")
    @ResponseStatus(HttpStatus.CREATED)
    public CartOrder createOrderFromCart(@RequestBody ShippingRequest shipping, @PathVariable Long id) {
        return cartOrderService.createOrder(id, shipping);
    }
}
