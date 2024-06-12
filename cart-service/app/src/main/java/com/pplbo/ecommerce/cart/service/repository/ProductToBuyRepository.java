package com.pplbo.ecommerce.cart.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pplbo.ecommerce.cart.service.model.ProductToBuy;

@Repository
public interface ProductToBuyRepository extends JpaRepository<ProductToBuy, Integer> {
}
