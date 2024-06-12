package com.pplbo.promotion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.promotion.model.Promotion;
import com.pplbo.promotion.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PromotionController.class)
public class PromotionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromotionService promotionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createPromotion_ShouldReturnCreatedPromotion() throws Exception {
        Promotion promotion = new Promotion();
        promotion.setName("Summer Sale");
        promotion.setStartDate(LocalDateTime.of(2024, 7, 1, 0, 0));
        promotion.setEndDate(LocalDateTime.of(2024, 7, 10, 0, 0));
        promotion.setPromotionType("discount");

        Mockito.when(promotionService.createPromotion(Mockito.any(Promotion.class))).thenReturn(promotion);

        mockMvc.perform(post("/api/promotions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(promotion)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(promotion)));
    }
}
