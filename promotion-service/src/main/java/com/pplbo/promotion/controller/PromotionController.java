package com.pplbo.promotion.controller;

import com.pplbo.promotion.model.Promotion;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }
}
