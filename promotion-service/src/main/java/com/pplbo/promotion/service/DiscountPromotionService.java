package com.pplbo.promotion.service;

import com.pplbo.promotion.exception.InvalidPromotionTypeException;
import com.pplbo.promotion.exception.ProductNotFoundException;
import com.pplbo.promotion.model.DiscountPromotion;
import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.DiscountPromotionRepository;
import com.pplbo.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class DiscountPromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private DiscountPromotionRepository discountPromotionRepository;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate

    public DiscountPromotion createDiscountPromotion(DiscountPromotion discountPromotion) {
        Long promotionId = discountPromotion.getPromotion().getId();
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));
        discountPromotion.setPromotion(promotion);

        // Check if the product exists
        if (!checkProductExists(discountPromotion.getProductId())) {
            throw new ProductNotFoundException("Product ID not found: " +
                    discountPromotion.getProductId());
        }

        discountPromotion.calculateDiscountedPrice();
        return discountPromotionRepository.save(discountPromotion);
    }

    public List<DiscountPromotion> getAllDiscountPromotions() {
        return discountPromotionRepository.findAll();
    }

    public DiscountPromotion getDiscountPromotionById(Long id) {
        return discountPromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiscountPromotion not found for id: " + id));
    }

    public DiscountPromotion getDiscountByProductId(Long id) {
        return discountPromotionRepository.findByProductId(id);
    }

    public void deleteDiscountPromotion(Long id) {
        DiscountPromotion discountPromotion = discountPromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiscountPromotion not found for this id :: " + id));
        discountPromotionRepository.delete(discountPromotion);
    }

    private void validatePromotionTypeForDiscountPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("discount")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for discount promotion");
        }
    }

    private boolean checkProductExists(Long productId) {
        try {
            String url = "http://localhost:8085/api/product/" + productId;
            restTemplate.getForObject(url, Object.class); // Assuming the endpoint returns 200 OK if the product exists
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
