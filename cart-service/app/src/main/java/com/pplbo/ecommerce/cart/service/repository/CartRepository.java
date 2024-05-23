package com.pplbo.ecommerce.cart.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pplbo.ecommerce.cart.service.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
