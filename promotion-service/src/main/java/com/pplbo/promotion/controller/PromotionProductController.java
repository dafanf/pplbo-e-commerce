package com.pplbo.promotion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/promotions")
public class PromotionProductController {
    @Autowired
    ProductPromotionService ProductPromotionService;

    @GetMapping("/product/{id}")
    public String getMethodName(@RequestParam Integer productId) {
        return new String();
    }

}
