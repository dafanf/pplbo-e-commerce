package com.pplbo.promotion.controller;

import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.service.B1G1PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/promotions/b1g1")
public class B1G1PromotionController {

    @Autowired
    private B1G1PromotionService promotionService;

    @PostMapping
    public ResponseEntity<B1G1Promotion> createB1G1Promotion(@Valid @RequestBody B1G1Promotion b1g1Promotion) {
        B1G1Promotion createdB1G1Promotion = promotionService.createB1G1Promotion(b1g1Promotion);
        return ResponseEntity.ok(createdB1G1Promotion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<B1G1Promotion> getB1G1PromotionById(@PathVariable Long id) {
        B1G1Promotion b1g1Promotion = promotionService.getB1G1PromotionById(id);
        return ResponseEntity.ok(b1g1Promotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteB1G1Promotion(@PathVariable Long id) {
        promotionService.deleteB1G1Promotion(id);
        return ResponseEntity.noContent().build();
    }
}
