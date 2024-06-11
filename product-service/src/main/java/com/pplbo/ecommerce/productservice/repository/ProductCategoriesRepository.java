package com.pplbo.ecommerce.productservice.repository;

import com.pplbo.ecommerce.productservice.model.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Integer> {
    List<ProductCategories> findByProductId(Integer productId);
}
