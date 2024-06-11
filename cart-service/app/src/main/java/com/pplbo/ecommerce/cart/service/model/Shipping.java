package com.pplbo.ecommerce.cart.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Shipping {
    private String shippingName;
    private double shippingPrice; // Use double for monetary values
    private String shippingStatus;
    private String shippingAddress;

    public Shipping(String shippingName, double shippingPrice, String shippingStatus, String shippingAddress) {
        this.shippingName = shippingName;
        this.shippingPrice = shippingPrice;
        this.shippingStatus = shippingStatus;
        this.shippingAddress = shippingAddress;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
