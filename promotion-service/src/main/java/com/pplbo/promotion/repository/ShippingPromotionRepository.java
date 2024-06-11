package com.pplbo.promotion.repository;

import com.pplbo.promotion.model.ShippingPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingPromotionRepository extends JpaRepository<ShippingPromotion, Long> {
}
