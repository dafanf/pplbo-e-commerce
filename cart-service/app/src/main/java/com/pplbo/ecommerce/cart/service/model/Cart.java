package com.pplbo.ecommerce.cart.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Entity
@Table(name = "cart", uniqueConstraints = @UniqueConstraint(columnNames = { "userid" }))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @lombok.NonNull
    private String userID;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cart")
    private List<ProductToBuy> productsToBuy = new ArrayList<>();

    private Long totalPrice;

    // Getters, setters, and other methods
    public void addProductToBuy(ProductToBuy productToBuy) {
        this.productsToBuy.add(productToBuy);
        productToBuy.setCart(this);
    }

    public void removeProductToBuy(ProductToBuy productToBuy) {
        this.productsToBuy.remove(productToBuy);
        productToBuy.setCart(null);
    }
}