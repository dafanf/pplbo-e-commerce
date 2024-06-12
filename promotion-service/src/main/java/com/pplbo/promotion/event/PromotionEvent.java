package com.pplbo.promotion.event;

import java.time.LocalDateTime;

public class PromotionEvent {
    private Long id;
    private String name;
    private String type;
    private String status;
    private String action;
    private LocalDateTime timestamp;
    // Atribut tambahan untuk B1G1
    private Long freeProductId;
    // Atribut tambahan untuk Discount
    private Long productId;
    private double discountedPrice;
    // Atribut tambahan untuk Shipping
    private double minimumOrderPrice;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Long getFreeProductId() {
        return freeProductId;
    }
    public void setFreeProductId(Long freeProductId) {
        this.freeProductId = freeProductId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public double getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public double getMinimumOrderPrice() {
        return minimumOrderPrice;
    }
    public void setMinimumOrderPrice(double minimumOrderPrice) {
        this.minimumOrderPrice = minimumOrderPrice;
    }
}

