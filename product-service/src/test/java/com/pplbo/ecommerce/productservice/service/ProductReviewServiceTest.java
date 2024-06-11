package com.pplbo.ecommerce.productservice.service;

import com.pplbo.ecommerce.productservice.dto.ProductReviewRequest;
import com.pplbo.ecommerce.productservice.dto.ProductReviewResponse;
import com.pplbo.ecommerce.productservice.model.ProductReview;
import com.pplbo.ecommerce.productservice.repository.ProductReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class ProductReviewServiceTest {

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    private ProductReviewRequest productReviewRequest;

    @BeforeEach
    void setUp() {
        productReviewRequest = new ProductReviewRequest(null, 5, "Great product!", 1); // Provide null for id if it's auto-generated
    }

    @Test
    void testCreateProductReview() {
        ProductReviewResponse createdReview = productReviewService.createProductReview(productReviewRequest);

        assertNotNull(createdReview);
        assertEquals(productReviewRequest.rating(), createdReview.rating());
        assertEquals(productReviewRequest.comment(), createdReview.comment());
        assertEquals(productReviewRequest.productId(), createdReview.productId());
    }

    @Test
    void testGetProductReviewById() {
        ProductReviewResponse createdReview = productReviewService.createProductReview(productReviewRequest);
        ProductReviewResponse foundReview = productReviewService.getProductReviewById(createdReview.id());

        assertNotNull(foundReview);
        assertEquals(createdReview.id(), foundReview.id());
    }

    @Test
    void testRemoveProductReview() {
        ProductReviewResponse createdReview = productReviewService.createProductReview(productReviewRequest);
        productReviewService.removeProductReview(createdReview.id());

        Optional<ProductReview> deletedReview = productReviewRepository.findById(createdReview.id());
        assertFalse(deletedReview.isPresent());
    }

    @Test
    void testGetAllProductReviews() {
        productReviewService.createProductReview(productReviewRequest);
        List<ProductReviewResponse> allReviews = productReviewService.getAllProductReviews();

        assertNotNull(allReviews);
        assertFalse(allReviews.isEmpty());
    }
}
