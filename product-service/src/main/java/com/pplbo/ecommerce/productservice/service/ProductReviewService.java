package com.pplbo.ecommerce.productservice.service;

import lombok.RequiredArgsConstructor;
import com.pplbo.ecommerce.productservice.repository.ProductReviewRepository;
import org.springframework.stereotype.Service;
import com.pplbo.ecommerce.productservice.dto.ProductReviewRequest;
import com.pplbo.ecommerce.productservice.dto.ProductReviewResponse;
import com.pplbo.ecommerce.productservice.model.ProductReview;

import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewService {
    
    private final ProductReviewRepository productReviewRepository;

    //Create product review
    public ProductReviewResponse createProductReview(ProductReviewRequest productReviewRequest) {
        ProductReview productReview = ProductReview.builder()
            .rating(productReviewRequest.rating())
            .comment(productReviewRequest.comment())
            .productId(productReviewRequest.productId())
            .build();
        productReviewRepository.save(productReview);
        return new ProductReviewResponse(productReview.getId(), productReview.getRating(), productReview.getComment(), productReview.getProductId());
    }

    //Get all product reviews
    public List<ProductReviewResponse> getAllProductReviews() {
        return productReviewRepository.findAll()
        .stream()
        .map(productReview -> new ProductReviewResponse(productReview.getId(), productReview.getRating(), productReview.getComment(), productReview.getProductId()))
        .collect(Collectors.toList());
    }

    //Remove product review by id
    public void removeProductReview(Integer id) {
        ProductReview productReview = productReviewRepository.findById(id).orElse(null);
        productReviewRepository.delete(productReview);
    }

    //get product review by id
    public ProductReviewResponse getProductReviewById(Integer id) {
        ProductReview productReview = productReviewRepository.findById(id).orElse(null);
        return new ProductReviewResponse(productReview.getId(), productReview.getRating(), productReview.getComment(), productReview.getProductId());
    }
}
