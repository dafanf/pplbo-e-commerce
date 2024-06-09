package com.pplbo.ecommerce.cart.service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.dto.ProductRequest;
import com.pplbo.ecommerce.cart.service.dto.ProductResponse;
import com.pplbo.ecommerce.cart.service.model.Product;
import com.pplbo.ecommerce.cart.service.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product generateProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .brandId(productRequest.brandId())
                .build();
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = generateProduct(productRequest);
        productRepository.save(product);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(),
                product.getBrandId(), product.getQuantityToBuy());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(),
                        product.getDescription(), product.getBrandId(), product.getQuantityToBuy()))
                .collect(Collectors.toList());
    }

    // Read operation - Get product by ID
    public ProductResponse getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return new ProductResponse(product.getId(), product.getName(), product.getPrice(),
                    product.getDescription(), product.getBrandId(), product.getQuantityToBuy());
        } else {
            // Handle the case where the product with the given ID does not exist
            return null;
        }
    }

    // Update operation
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productRequest.name());
            product.setPrice(productRequest.price());
            product.setDescription(productRequest.description());
            product.setBrandId(productRequest.brandId());
            product.setQuantityToBuy(productRequest.quantityToBuy());
            productRepository.save(product);
            return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(),
                    product.getBrandId(), product.getQuantityToBuy());
        } else {
            // Handle the case where the product with the given ID does not exist
            return null;
        }
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {

        }
        productRepository.deleteById(id);
    }

}