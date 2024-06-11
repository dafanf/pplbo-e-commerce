package com.pplbo.ecommerce.cart.service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.dto.ProductRequest;
import com.pplbo.ecommerce.cart.service.dto.ProductResponse;
import com.pplbo.ecommerce.cart.service.model.Product;
import com.pplbo.ecommerce.cart.service.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {

        }
    }
}