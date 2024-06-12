package com.pplbo.ecommerce.productservice.controller;

import com.pplbo.ecommerce.productservice.dto.*;
import com.pplbo.ecommerce.productservice.model.Product;
import com.pplbo.ecommerce.productservice.model.ProductCategories;
import com.pplbo.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ProductDetailResponse getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategoriesResponse addCategoryToProduct(@PathVariable Integer id, @RequestBody ProductCategoriesRequest productCategoriesRequest){
        return productService.addCategoryToProduct(id, productCategoriesRequest);
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategoriesResponse getCategoryOfProduct(@PathVariable Integer id){
        return productService.getCategoryOfProduct(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDetailResponse> getAllProductDetail(){
        return productService.getAllProductDetail();
    }
}
