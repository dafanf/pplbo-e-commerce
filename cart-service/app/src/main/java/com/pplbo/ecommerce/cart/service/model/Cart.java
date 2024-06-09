package com.pplbo.ecommerce.cart.service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private String userID;
    private List<ProductCart> products;
    private List<ProductCart> selectedProducts;
    private Integer totalPrice;

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<ProductCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCart> products) {
        this.products = products;
    }

    public List<ProductCart> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<ProductCart> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

}
