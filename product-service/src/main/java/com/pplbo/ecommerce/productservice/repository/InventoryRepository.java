package com.pplbo.ecommerce.productservice.repository;

import com.pplbo.ecommerce.productservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByProductName(String productName);
}