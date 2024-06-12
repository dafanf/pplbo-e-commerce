package com.pplbo.ecommerce.productservice.repository;

import com.pplbo.ecommerce.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);
    List<Category> findByCategoryIdIn(List<Integer> categoryIds);
}