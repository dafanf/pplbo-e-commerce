package com.pplbo.ecommerce.productservice.dto;

import com.pplbo.ecommerce.productservice.model.Inventory;

public record InventoryRequest(String productName, Integer quantity) {
    
}