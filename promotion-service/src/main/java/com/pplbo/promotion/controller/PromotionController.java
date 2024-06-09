package com.pplbo.promotion.controller;

import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.model.ShippingPromotion;
import com.pplbo.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@Valid @RequestBody Promotion promotion) {
        Promotion createdPromotion = promotionService.createPromotion(promotion);
        return ResponseEntity.ok(createdPromotion);
    }

    @PostMapping("/discount")
    public ResponseEntity<DiscountPromotion> createDiscountPromotion(@Valid @RequestBody DiscountPromotion discountPromotion) {
        DiscountPromotion createdDiscountPromotion = promotionService.createDiscountPromotion(discountPromotion);
        return ResponseEntity.ok(createdDiscountPromotion);
    }

    @PostMapping("/b1g1")
    public ResponseEntity<B1G1Promotion> createB1G1Promotion(@Valid @RequestBody B1G1Promotion b1g1Promotion) {
        B1G1Promotion createdB1G1Promotion = promotionService.createB1G1Promotion(b1g1Promotion);
        return ResponseEntity.ok(createdB1G1Promotion);
    }

    @PostMapping("/shipping")
    public ResponseEntity<ShippingPromotion> createShippingPromotion(@Valid @RequestBody ShippingPromotion shippingPromotion) {
        ShippingPromotion createdShippingPromotion = promotionService.createShippingPromotion(shippingPromotion);
        return ResponseEntity.ok(createdShippingPromotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Long id, @RequestBody Promotion promotionDetails) {
        Promotion updatedPromotion = promotionService.updatePromotion(id, promotionDetails);
        return ResponseEntity.ok(updatedPromotion);
    }

    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long id) {
        Promotion promotion = promotionService.getPromotionById(id);
        return ResponseEntity.ok(promotion);
    }

    @GetMapping("/discounts")
    public List<DiscountPromotion> getAllDiscountPromotions() {
        return promotionService.getAllDiscountPromotions();
    }

    @GetMapping("/b1g1")
    public List<B1G1Promotion> getAllB1G1Promotions() {
        return promotionService.getAllB1G1Promotions();
    }

    @GetMapping("/shippings")
    public List<ShippingPromotion> getAllShippingPromotions() {
        return promotionService.getAllShippingPromotions();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Void> deleteDiscountPromotion(@PathVariable Long id) {
        promotionService.deleteDiscountPromotion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/b1g1/{id}")
    public ResponseEntity<Void> deleteB1G1Promotion(@PathVariable Long id) {
        promotionService.deleteB1G1Promotion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/shipping/{id}")
    public ResponseEntity<Void> deleteShippingPromotion(@PathVariable Long id) {
        promotionService.deleteShippingPromotion(id);
        return ResponseEntity.noContent().build();
    }
}
