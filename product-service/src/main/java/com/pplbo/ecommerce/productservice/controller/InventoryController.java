package com.pplbo.ecommerce.productservice.controller;

import com.pplbo.ecommerce.productservice.model.Inventory;
import com.pplbo.ecommerce.productservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/inventory")
@RequiredArgsConstructor

public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory addInventory(@RequestBody Inventory inventory){
        return inventoryService.addInventory(inventory);
    }

    @GetMapping("/all-inventory")
    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{productName}")
    public Inventory getInventoryByProductName(@PathVariable("productName") String productName){
        return inventoryService.getInventoryByProductName(productName);
    }

    @PutMapping("/decrease-stock/{productName}")
    public Inventory decreaseInventory(@PathVariable("productName") String productName, @RequestParam Integer decreasequantity){
        return inventoryService.decreaseInventory(productName, decreasequantity);
    }

    @GetMapping("/check-stock/{productName}")
    public Boolean checkStok(@PathVariable("productName") String productName){
        return inventoryService.checkStok(productName);
    }

    @PutMapping("/recover/{productName}")
    public Inventory recoverInventory(@PathVariable("productName") String productName, @RequestParam Integer recoverquantity){
        Inventory existInventory = inventoryService.getInventoryByProductName(productName);
        return inventoryService.recoverInventory(existInventory, recoverquantity);
    }
}