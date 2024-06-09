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
        discountPromotion.calculateDiscountedPrice();
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
    public ResponseEntity<?> updatePromotion(@PathVariable Long id, @RequestBody Promotion promotionDetails) {
        try {
            Promotion updatedPromotion = promotionService.updatePromotion(id, promotionDetails);
            return ResponseEntity.ok(updatedPromotion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
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

    @GetMapping("/discount/{id}")
    public ResponseEntity<DiscountPromotion> getDiscountPromotionById(@PathVariable Long id) {
        DiscountPromotion discountPromotion = promotionService.getDiscountPromotionById(id);
        return ResponseEntity.ok(discountPromotion);
    }

    @GetMapping("/b1g1/{id}")
    public ResponseEntity<B1G1Promotion> getB1G1PromotionById(@PathVariable Long id) {
        B1G1Promotion b1g1Promotion = promotionService.getB1G1PromotionById(id);
        return ResponseEntity.ok(b1g1Promotion);
    }

    @GetMapping("/shipping/{id}")
    public ResponseEntity<ShippingPromotion> getShippingPromotionById(@PathVariable Long id) {
        ShippingPromotion shippingPromotion = promotionService.getShippingPromotionById(id);
        return ResponseEntity.ok(shippingPromotion);
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