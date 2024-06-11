package com.pplbo.promotion.service;

import com.pplbo.promotion.model.ShippingPromotion;
import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.ShippingPromotionRepository;
import com.pplbo.promotion.repository.PromotionRepository;
import com.pplbo.promotion.exception.InvalidPromotionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingPromotionService {

    @Autowired
    private ShippingPromotionRepository shippingPromotionRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public ShippingPromotion createShippingPromotion(ShippingPromotion shippingPromotion) {
        validatePromotionTypeForShippingPromotion(shippingPromotion.getPromotion().getId());
        return shippingPromotionRepository.save(shippingPromotion);
    }

    public ShippingPromotion getShippingPromotionById(Long id) {
        return shippingPromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipping Promotion not found for id: " + id));
    }

    public void deleteShippingPromotion(Long id) {
        ShippingPromotion shippingPromotion = shippingPromotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ShippingPromotion not found for this id :: " + id));
        shippingPromotionRepository.delete(shippingPromotion);
    }

    private void validatePromotionTypeForShippingPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
            .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("shipping")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for shipping promotion");
        }
    }
}
