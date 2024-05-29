package com.pplbo.promotion.repository;

import com.pplbo.promotion.model.DiscountPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPromotionRepository extends JpaRepository<DiscountPromotion, Long> {
}