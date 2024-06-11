package com.pplbo.ecommerce.productservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.pplbo.ecommerce.productservice.service.ProductReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import com.pplbo.ecommerce.productservice.model.ProductReview;
import com.pplbo.ecommerce.productservice.dto.ProductReviewRequest;
import com.pplbo.ecommerce.productservice.dto.ProductReviewResponse;

import java.util.List;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/product/review")
@RequiredArgsConstructor

public class ProductReviewController {
    
    private final ProductReviewService productReviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReviewResponse createProductReview(@RequestBody ProductReviewRequest productReviewRequest) {
        return productReviewService.createProductReview(productReviewRequest);
    }

    @GetMapping("/all")
    public List<ProductReviewResponse> getAllProductReviews() {
        return productReviewService.getAllProductReviews();
    }

    @GetMapping("/{id}")
    public ProductReviewResponse getProductReviewById(@PathVariable("id") int id) {
        return productReviewService.getProductReviewById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductReview(@PathVariable("id") Integer id) {
        productReviewService.removeProductReview(id);
    }
}
