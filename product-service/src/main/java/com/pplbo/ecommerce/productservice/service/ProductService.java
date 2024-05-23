package com.pplbo.ecommerce.productservice.service;

import com.pplbo.ecommerce.productservice.dto.ProductRequest;
import com.pplbo.ecommerce.productservice.model.Product;
import com.pplbo.ecommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .brandId(productRequest.brandId())
                .build();
        productRepository.save(product);

        return product;
    }
}