package com.pplbo.promotion.service;

import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.B1G1PromotionRepository;
import com.pplbo.promotion.repository.PromotionRepository;
import com.pplbo.promotion.exception.InvalidPromotionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class B1G1PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private B1G1PromotionRepository b1g1PromotionRepository;

    public B1G1Promotion createB1G1Promotion(B1G1Promotion b1g1Promotion) {
        validatePromotionTypeForB1G1Promotion(b1g1Promotion.getPromotion().getId());
        return b1g1PromotionRepository.save(b1g1Promotion);
    }

    public B1G1Promotion getB1G1PromotionById(Long id) {
        return b1g1PromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("B1G1 Promotion not found for id: " + id));
    }

    public void deleteB1G1Promotion(Long id) {
        B1G1Promotion b1g1Promotion = b1g1PromotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("B1G1Promotion not found for this id :: " + id));
        b1g1PromotionRepository.delete(b1g1Promotion);
    }

    private void validatePromotionTypeForB1G1Promotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
            .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("b1g1")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for B1G1 promotion");
        }
    }
}
