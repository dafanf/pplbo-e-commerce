package com.pplbo.ecommerce.productservice.repository;

import com.pplbo.ecommerce.productservice.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

}
