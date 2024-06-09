package com.pplbo.promotion.service;

import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.repository.PromotionRepository;
import com.pplbo.promotion.repository.DiscountPromotionRepository;
import com.pplbo.promotion.repository.B1G1PromotionRepository;
import com.pplbo.promotion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private DiscountPromotionRepository discountPromotionRepository;

    @Autowired
    private B1G1PromotionRepository b1g1PromotionRepository;

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public DiscountPromotion createDiscountPromotion(DiscountPromotion discountPromotion) {
        return discountPromotionRepository.save(discountPromotion);
    }

    public B1G1Promotion createB1G1Promotion(B1G1Promotion b1g1Promotion) {
        return b1g1PromotionRepository.save(b1g1Promotion);
    }

    public Promotion updatePromotion(Long id, Promotion promotionDetails) {
        Promotion promotion = promotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promotion not found for this id :: " + id));

        promotion.setName(promotionDetails.getName());
        promotion.setStartDate(promotionDetails.getStartDate());
        promotion.setEndDate(promotionDetails.getEndDate());
        promotion.setPromotionType(promotionDetails.getPromotionType());

        return promotionRepository.save(promotion);
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promotion not found for this id :: " + id));
    }

    public void deletePromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promotion not found for this id :: " + id));
        promotionRepository.delete(promotion);
    }

    public void deleteDiscountPromotion(Long id) {
        DiscountPromotion discountPromotion = discountPromotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("DiscountPromotion not found for this id :: " + id));
        discountPromotionRepository.delete(discountPromotion);
    }

    public void deleteB1G1Promotion(Long id) {
        B1G1Promotion b1g1Promotion = b1g1PromotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("B1G1Promotion not found for this id :: " + id));
        b1g1PromotionRepository.delete(b1g1Promotion);
    }
}