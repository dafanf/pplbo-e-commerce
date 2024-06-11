package com.pplbo.promotion.service;

import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.DiscountPromotionRepository;
import com.pplbo.promotion.repository.PromotionRepository;
import com.pplbo.promotion.exception.InvalidPromotionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountPromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private DiscountPromotionRepository discountPromotionRepository;

    public DiscountPromotion createDiscountPromotion(DiscountPromotion discountPromotion) {
        Long promotionId = discountPromotion.getPromotion().getId();
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));
        discountPromotion.setPromotion(promotion);
        discountPromotion.calculateDiscountedPrice();

        return discountPromotionRepository.save(discountPromotion);
    }

    public DiscountPromotion getDiscountPromotionById(Long id) {
        return discountPromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiscountPromotion not found for id: " + id));
    }

    public void deleteDiscountPromotion(Long id) {
        DiscountPromotion discountPromotion = discountPromotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DiscountPromotion not found for this id :: " + id));
        discountPromotionRepository.delete(discountPromotion);
    }

    private void validatePromotionTypeForDiscountPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
            .orElseThrow(() -> new RuntimeException("Promotion not found for id: "
            + promotionId));

            if (!promotion.getPromotionType().equalsIgnoreCase("discount")) {
                throw new InvalidPromotionTypeException("Invalid promotion type for discount promotion");
            }
        }
    }
    