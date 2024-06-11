package com.pplbo.promotion.controller;

import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.service.DiscountPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/promotions/discount")
public class DiscountPromotionController {

    @Autowired
    private DiscountPromotionService discountPromotionService;

    @PostMapping
    public ResponseEntity<DiscountPromotion> createDiscountPromotion(@Valid @RequestBody DiscountPromotion discountPromotion) {
        discountPromotion.calculateDiscountedPrice();
        DiscountPromotion createdDiscountPromotion = discountPromotionService.createDiscountPromotion(discountPromotion);
        return ResponseEntity.ok(createdDiscountPromotion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountPromotion> getDiscountPromotionById(@PathVariable Long id) {
        DiscountPromotion discountPromotion = discountPromotionService.getDiscountPromotionById(id);
        return ResponseEntity.ok(discountPromotion);
    }

    @GetMapping
    public ResponseEntity<List<DiscountPromotion>> getAllDiscountPromotions() {
        List<DiscountPromotion> discountPromotions = discountPromotionService.getAllDiscountPromotions();
        return ResponseEntity.ok(discountPromotions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountPromotion(@PathVariable Long id) {
        discountPromotionService.deleteDiscountPromotion(id);
        return ResponseEntity.noContent().build();
    }
}
