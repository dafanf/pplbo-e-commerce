package com.pplbo.promotion.controller;

import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.service.DiscountPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/promotions/discount")
public class DiscountPromotionController {

    @Autowired
    private DiscountPromotionService promotionService;

    @PostMapping
    public ResponseEntity<DiscountPromotion> createDiscountPromotion(@Valid @RequestBody DiscountPromotion discountPromotion) {
        discountPromotion.calculateDiscountedPrice();
        DiscountPromotion createdDiscountPromotion = promotionService.createDiscountPromotion(discountPromotion);
        return ResponseEntity.ok(createdDiscountPromotion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountPromotion> getDiscountPromotionById(@PathVariable Long id) {
        DiscountPromotion discountPromotion = promotionService.getDiscountPromotionById(id);
        return ResponseEntity.ok(discountPromotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountPromotion(@PathVariable Long id) {
        promotionService.deleteDiscountPromotion(id);
        return ResponseEntity.noContent().build();
    }
}
