package com.pplbo.promotion.controller;

import com.pplbo.promotion.model.ShippingPromotion;
import com.pplbo.promotion.service.ShippingPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/promotions/shipping")
public class ShippingPromotionController {

    @Autowired
    private ShippingPromotionService promotionService;

    @PostMapping
    public ResponseEntity<ShippingPromotion> createShippingPromotion(@Valid @RequestBody ShippingPromotion shippingPromotion) {
        ShippingPromotion createdShippingPromotion = promotionService.createShippingPromotion(shippingPromotion);
        return ResponseEntity.ok(createdShippingPromotion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingPromotion> getShippingPromotionById(@PathVariable Long id) {
        ShippingPromotion shippingPromotion = promotionService.getShippingPromotionById(id);
        return ResponseEntity.ok(shippingPromotion);
    }

    @GetMapping
    public ResponseEntity<List<ShippingPromotion>> getAllShippingPromotions() {
        List<ShippingPromotion> shippingPromotions = promotionService.getAllShippingPromotions();
        return ResponseEntity.ok(shippingPromotions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingPromotion(@PathVariable Long id) {
        promotionService.deleteShippingPromotion(id);
        return ResponseEntity.noContent().build();
    }
}
