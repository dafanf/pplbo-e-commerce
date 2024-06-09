package com.pplbo.promotion.model;

import jakarta.persistence.*;

@Entity
public class ShippingPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    @NotNull(message = "Promotion is mandatory")
    private Promotion promotion;

    @NotNull(message = "Minimum order price is mandatory")
    private double minimumOrderPrice;

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

    public double getMinimumOrderPrice() {
        return minimumOrderPrice;
    }

    public void setMinimumOrderPrice(double minimumOrderPrice) {
        this.minimumOrderPrice = minimumOrderPrice;
    }
}
