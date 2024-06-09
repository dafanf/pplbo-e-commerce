package com.pplbo.ecommerce.productservice.repository;

import com.pplbo.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
