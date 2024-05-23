package com.pplbo.ecommerce.cart.service.model;

public class ProductCart extends Product {
    private int quantityToBuy;

    public int getQuantityToBuy() {
        return quantityToBuy;
    }

    public void setQuantityToBuy(int quantityToBuy) {
        this.quantityToBuy = quantityToBuy;
    }

}
