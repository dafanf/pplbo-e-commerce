package com.pplbo.promotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplbo.promotion.dto.ProductPromoted;
import com.pplbo.promotion.model.DiscountPromotion;

@Service
public class ProductPromotionService {
    @Autowired
    B1G1PromotionService b1g1PromotionService;
    DiscountPromotionService discountPromotionService;
    ShippingPromotionService shippingPromotionService;

    ProductPromoted getProductPromotion(Long productId) {
        Double discount = discountPromotionService.getDiscountByProductId(productId.longValue()).getDiscountedPrice();
        Integer quantity = b1g1PromotionService.getB1G1PromotionById(productId).g;
        return new ProductPromoted(productId, , null,discount.longValue())
    }

}
