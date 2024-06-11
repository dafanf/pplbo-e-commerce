package com.pplbo.promotion.repository;

import com.pplbo.promotion.model.B1G1Promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface B1G1PromotionRepository extends JpaRepository<B1G1Promotion, Long> {
    B1G1Promotion findByProductId(Long productId);
}