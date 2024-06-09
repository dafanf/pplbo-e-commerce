package com.pplbo.promotion.service;

import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.model.ShippingPromotion;
import com.pplbo.promotion.repository.PromotionRepository;
import com.pplbo.promotion.repository.DiscountPromotionRepository;
import com.pplbo.promotion.repository.B1G1PromotionRepository;
import com.pplbo.promotion.repository.ShippingPromotionRepository;
import com.pplbo.promotion.exception.InvalidPromotionTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private DiscountPromotionRepository discountPromotionRepository;

    @Autowired
    private B1G1PromotionRepository b1g1PromotionRepository;

    @Autowired
    private ShippingPromotionRepository shippingPromotionRepository;

    public Promotion createPromotion(Promotion promotion) {
        validatePromotionType(promotion.getPromotionType());
        promotion.updateStatus();
        return promotionRepository.save(promotion);
    }

    public DiscountPromotion createDiscountPromotion(DiscountPromotion discountPromotion) {
        Long promotionId = discountPromotion.getPromotion().getId();
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));
        discountPromotion.setPromotion(promotion);
        discountPromotion.calculateDiscountedPrice();

        return discountPromotionRepository.save(discountPromotion);
    }

    public B1G1Promotion createB1G1Promotion(B1G1Promotion b1g1Promotion) {
        validatePromotionTypeForB1G1Promotion(b1g1Promotion.getPromotion().getId());
        return b1g1PromotionRepository.save(b1g1Promotion);
    }

    public ShippingPromotion createShippingPromotion(ShippingPromotion shippingPromotion) {
        validatePromotionTypeForShippingPromotion(shippingPromotion.getPromotion().getId());
        return shippingPromotionRepository.save(shippingPromotion);
    }

    private void validatePromotionType(String promotionType) {
        if (!promotionType.equalsIgnoreCase("discount") && !promotionType.equalsIgnoreCase("b1g1") && !promotionType.equalsIgnoreCase("shipping")) {
            throw new InvalidPromotionTypeException("Invalid promotion type: " + promotionType);
        }
    }

    private void validatePromotionTypeForDiscountPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
            .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("discount")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for discount promotion");
        }
    }

    private void validatePromotionTypeForB1G1Promotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
            .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("b1g1")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for B1G1 promotion");
        }
    }

    private void validatePromotionTypeForShippingPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
            .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("shipping")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for shipping promotion");
        }
    }

    public Promotion updatePromotion(Long id, Promotion promotionDetails) {
        Promotion promotion = promotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Promotion not found for this id :: " + id));
    
        boolean updated = false;
        
        if (promotionDetails.getStartDate() != null) {
            promotion.setStartDate(promotionDetails.getStartDate());
            updated = true;
        }
        if (promotionDetails.getEndDate() != null) {
            promotion.setEndDate(promotionDetails.getEndDate());
            updated = true;
        }
    
        if (!updated) {
            throw new IllegalArgumentException("Only startDate and endDate can be updated.");
        }
    
        promotion.updateStatus();
        return promotionRepository.save(promotion);
    }
    

    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        promotions.forEach(Promotion::updateStatus);
        return promotions.stream().map(promotionRepository::save).collect(Collectors.toList());
    }

    public Promotion getPromotionById(Long id) {
        Promotion promotion = promotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Promotion not found for this id :: " + id));
        promotion.updateStatus();
        return promotionRepository.save(promotion);
    }

    public void deletePromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Promotion not found for this id :: " + id));
        promotionRepository.delete(promotion);
    }

    public void deleteDiscountPromotion(Long id) {
        DiscountPromotion discountPromotion = discountPromotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DiscountPromotion not found for this id :: " + id));
        discountPromotionRepository.delete(discountPromotion);
    }

    public void deleteB1G1Promotion(Long id) {
        B1G1Promotion b1g1Promotion = b1g1PromotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("B1G1Promotion not found for this id :: " + id));
        b1g1PromotionRepository.delete(b1g1Promotion);
    }

    public void deleteShippingPromotion(Long id) {
        ShippingPromotion shippingPromotion = shippingPromotionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ShippingPromotion not found for this id :: " + id));
        shippingPromotionRepository.delete(shippingPromotion);
    }
}
