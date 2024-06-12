package com.pplbo.promotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class DiscountPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    @NotNull(message = "Promotion is mandatory")
    private Promotion promotion;

    @NotNull(message = "Discount percentage is mandatory")
    private double discountPercentage;

    @NotNull(message = "Maximum discount amount is mandatory")
    private double maximumDiscountAmount;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    private double originalPrice;

    private double discountedPrice;

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

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getMaximumDiscountAmount() {
        return maximumDiscountAmount;
    }

    public void setMaximumDiscountAmount(double maximumDiscountAmount) {
        this.maximumDiscountAmount = maximumDiscountAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }    

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    // public void calculateDiscountedPrice() {
    //     this.discountedPrice = this.originalPrice - (this.originalPrice * this.discountPercentage / 100);
    //     if (this.discountedPrice > this.maximumDiscountAmount) {
    //         this.discountedPrice = this.maximumDiscountAmount;
    //     }
    // }
    
    public void calculateDiscountedPrice() {
        double discountAmount = this.originalPrice * (this.discountPercentage / 100);
        
        // Apply maximum discount amount
        if (discountAmount > this.maximumDiscountAmount) {
            discountAmount = this.maximumDiscountAmount;
        }
    
        this.discountedPrice = this.originalPrice - discountAmount;
    }
    
}