package com.pplbo.ecommerce.productservice.service;

import lombok.RequiredArgsConstructor;
import com.pplbo.ecommerce.productservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import com.pplbo.ecommerce.productservice.model.Inventory;

import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor

public class InventoryService {
    private final InventoryRepository inventoryRepository;

    //add inventory
    public Inventory addInventory(Inventory inventory) {
        Inventory newInventory = Inventory.builder()
            .productName(inventory.getProductName())
            .productId(inventory.getProductId())
            .quantity(inventory.getQuantity())
            .build();
        inventoryRepository.save(newInventory);

        return newInventory;
    }

    //get all inventories
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    //get inventory by product name
    public Inventory getInventoryByProductName(String productName) {
        return inventoryRepository.findByProductName(productName);
    }

    public Inventory getInventoryByProductId(Integer productId) {
        return inventoryRepository.findByProductId(productId);
    }

    //decrease inventory
    public Inventory decreaseInventory(Integer productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
        return inventory;
    }

    //check stok
    public Boolean checkStok(Integer productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory.getQuantity() >= quantity;
    }

    //recover inventory
    public Inventory recoverInventory(Inventory existInventory, Integer quantity) {
        existInventory.setQuantity(existInventory.getQuantity() + quantity);
        inventoryRepository.save(existInventory);
        return existInventory;
    }
}