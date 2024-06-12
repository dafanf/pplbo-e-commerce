package com.pplbo.promotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class B1G1Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    @NotNull(message = "Promotion is mandatory")
    private Promotion promotion;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotNull(message = "Free Product ID is mandatory")
    private Long freeProductId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getFreeProductId() {
        return freeProductId;
    }

    public void setFreeProductId(Long freeProductId) {
        this.freeProductId = freeProductId;
    }
}