package com.pplbo.promotion.service;

import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion createPromotion(Promotion promotion) {
        System.out.println("create");
        validatePromotionType(promotion.getPromotionType());
        promotion.updateStatus();
        return promotionRepository.save(promotion);
    }

    protected void validatePromotionType(String promotionType) {
        if (!promotionType.equalsIgnoreCase("discount") && !promotionType.equalsIgnoreCase("b1g1")
                && !promotionType.equalsIgnoreCase("shipping")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid promotion type: " + promotionType);
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

    // Setter method for testing
    public void setPromotionRepository(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }
}
