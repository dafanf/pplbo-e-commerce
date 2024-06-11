package com.pplbo.ecommerce.cart.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.model.ProductToBuy;
import com.pplbo.ecommerce.cart.service.repository.ProductToBuyRepository;

@Service
public class ProductToBuyService {

    @Autowired
    private ProductToBuyRepository productToBuyRepository;

    public List<ProductToBuy> getAllProductToBuys() {
        return productToBuyRepository.findAll();
    }

    public ProductToBuy getProductToBuyById(Long id) {
        return productToBuyRepository.findById(id).get();
    }

    public ProductToBuy saveProductToBuy(ProductToBuy productToBuy) {
        return productToBuyRepository.save(productToBuy);
    }

    public void deleteProductToBuyById(Long id) {
        productToBuyRepository.deleteById(id);
    }
}