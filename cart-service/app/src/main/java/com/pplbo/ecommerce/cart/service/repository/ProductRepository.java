package com.pplbo.ecommerce.cart.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pplbo.ecommerce.cart.service.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
