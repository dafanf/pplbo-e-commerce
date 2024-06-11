package com.pplbo.promotion.service;

import com.pplbo.promotion.exception.InvalidPromotionTypeException;
import com.pplbo.promotion.exception.ProductNotFoundException;
import com.pplbo.promotion.model.B1G1Promotion;
import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.B1G1PromotionRepository;
import com.pplbo.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class B1G1PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private B1G1PromotionRepository b1g1PromotionRepository;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate

    public B1G1Promotion createB1G1Promotion(B1G1Promotion b1g1Promotion) {
        validatePromotionTypeForB1G1Promotion(b1g1Promotion.getPromotion().getId());

        // // Check if product and free product exist
        // if (!checkProductExists(b1g1Promotion.getProductId())) {
        // throw new ProductNotFoundException("Product ID not found: " +
        // b1g1Promotion.getProductId());
        // }
        // if (!checkProductExists(b1g1Promotion.getFreeProductId())) {
        // throw new ProductNotFoundException("Free Product ID not found: " +
        // b1g1Promotion.getFreeProductId());
        // }

        return b1g1PromotionRepository.save(b1g1Promotion);
    }

    public B1G1Promotion getB1G1PromotionById(Long id) {
        return b1g1PromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("B1G1 Promotion not found for id: " + id));
    }

    public List<B1G1Promotion> getAllB1G1Promotions() {
        return b1g1PromotionRepository.findAll();
    }

    public void deleteB1G1Promotion(Long id) {
        B1G1Promotion b1g1Promotion = b1g1PromotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("B1G1Promotion not found for this id :: " + id));
        b1g1PromotionRepository.delete(b1g1Promotion);
    }

    public B1G1Promotion getPromotionByProductId(Long id) {
        return b1g1PromotionRepository.findByProductId(id.longValue());

    }

    private void validatePromotionTypeForB1G1Promotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found for id: " + promotionId));

        if (!promotion.getPromotionType().equalsIgnoreCase("b1g1")) {
            throw new InvalidPromotionTypeException("Invalid promotion type for B1G1 promotion");
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
