package com.pplbo.ecommerce.productservice.repository;

import com.pplbo.ecommerce.productservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
