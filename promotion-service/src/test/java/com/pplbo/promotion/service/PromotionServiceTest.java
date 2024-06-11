package com.pplbo.promotion.service;

import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.repository.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class PromotionServiceTest {

    private PromotionService promotionService;
    private PromotionRepository promotionRepository;

    @BeforeEach
    public void setUp() {
        promotionRepository = Mockito.mock(PromotionRepository.class);
        promotionService = new PromotionService();
        promotionService.setPromotionRepository(promotionRepository); // Injecting mock repository
    }

    @Test
    public void createPromotion_ShouldReturnCreatedPromotion() {
        Promotion promotion = new Promotion();
        promotion.setName("Summer Sale");
        promotion.setStartDate(LocalDateTime.of(2024, 7, 1, 0, 0));
        promotion.setEndDate(LocalDateTime.of(2024, 7, 10, 0, 0));
        promotion.setPromotionType("discount");

        Mockito.when(promotionRepository.save(any(Promotion.class))).thenReturn(promotion);

        Promotion createdPromotion = promotionService.createPromotion(promotion);

        assertEquals("Summer Sale", createdPromotion.getName());
        assertEquals("non-active", createdPromotion.getStatus());
    }

    @Test
    public void validatePromotionType_ShouldNotThrowExceptionForValidType() {
        String validType = "discount";

        // This method should not throw an exception
        promotionService.validatePromotionType(validType);
    }

    @Test
    public void validatePromotionType_ShouldThrowExceptionForInvalidType() {
        String invalidType = "invalidType";

        try {
            promotionService.validatePromotionType(invalidType);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getStatusCode().value());
            assertEquals("Invalid promotion type: " + invalidType, ex.getReason());
        }
    }

}
