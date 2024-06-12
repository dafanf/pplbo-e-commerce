package com.pplbo.promotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplbo.promotion.dto.ProductPromoted;
import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.model.DiscountPromotion;

@Service
public class ProductPromotionService {
    @Autowired
    B1G1PromotionService b1g1PromotionService;
    @Autowired
    DiscountPromotionService discountPromotionService;

    public ProductPromoted getProductPromotion(Long productId) {
        DiscountPromotion discount = discountPromotionService.getDiscountByProductId(productId.longValue());
        B1G1Promotion b1g1 = b1g1PromotionService.getPromotionByProductId(productId);
        Double price = (double) -1;
        Long bonusProductId = null;
        if (b1g1 != null) {
            bonusProductId = b1g1.getFreeProductId();
        }
        if (discount != null) {
            price = discount.getDiscountedPrice();
        }
        return new ProductPromoted(productId, price.longValue(), bonusProductId);
    }

}
