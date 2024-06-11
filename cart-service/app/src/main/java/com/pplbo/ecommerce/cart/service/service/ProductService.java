package com.pplbo.ecommerce.cart.service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import com.pplbo.ecommerce.cart.service.dto.ProductRequest;
import com.pplbo.ecommerce.cart.service.dto.ProductResponse;
import com.pplbo.ecommerce.cart.service.model.Product;
import com.pplbo.ecommerce.cart.service.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Arrays; // Added import for Arrays

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    
    private RestTemplate restTemplate = new RestTemplate();

    private final String PRODUCT_API_URL = "http://localhost:8085/api/product/";

    public List<Product> getAllProducts() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity(PRODUCT_API_URL + "all", Product[].class);
        return Arrays.asList(response.getBody());
    }

    public Optional<Product> getProductById(Integer id) {
        ResponseEntity<Product> response = restTemplate.getForEntity(PRODUCT_API_URL + id, Product.class);
        return Optional.ofNullable(response.getBody());
    }

    public Product createProduct(Product product) {
        return restTemplate.postForObject(PRODUCT_API_URL, product, Product.class);
    }

    public Product updateProduct(Integer id, Product product) {
        restTemplate.put(PRODUCT_API_URL + id, product);
        return product;
    }

    public void deleteProduct(Integer id) {
        restTemplate.delete(PRODUCT_API_URL + id);
    }


    public List<Product> getAllProductsInCategory(Integer categoryId) {
        ResponseEntity<Product[]> response = restTemplate.getForEntity(PRODUCT_API_URL + "category/" + categoryId, Product[].class);
        return Arrays.asList(response.getBody());
    }

    public Product createProductInCategory(Integer categoryId, Product product) {
        return restTemplate.postForObject(PRODUCT_API_URL + "category/" + categoryId, product, Product.class);
    }

    public Product updateProductInCategory(Integer categoryId, Integer productId, Product product) {
        restTemplate.put(PRODUCT_API_URL + "category/" + categoryId + "/" + productId, product);
        return product;
    }

    public void deleteProductInCategory(Integer categoryId, Integer productId) {
        restTemplate.delete(PRODUCT_API_URL + "category/" + categoryId + "/" + productId);
    }
}
