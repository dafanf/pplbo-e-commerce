package com.pplbo.ecommerce.cart.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

public class LineItem {
    private Long productId;
    private Integer quantity;

    public LineItem(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
