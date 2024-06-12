package com.pplbo.promotion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.pplbo.promotion.dto.ProductPromoted;
import com.pplbo.promotion.service.ProductPromotionService;

@RestController
@RequestMapping("/api/promotions")
public class PromotionProductController {
    @Autowired
    ProductPromotionService productPromotionService;

    @GetMapping("/products/{productId}")
    public ProductPromoted getProductPromotion(@PathVariable Long productId) {
        return productPromotionService.getProductPromotion(productId);
    }

}
