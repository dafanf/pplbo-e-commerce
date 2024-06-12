package com.pplbo.ecommerce.productservice.service;

import com.pplbo.ecommerce.productservice.dto.*;
import com.pplbo.ecommerce.productservice.model.Brand;
import com.pplbo.ecommerce.productservice.model.Category;
import com.pplbo.ecommerce.productservice.model.Product;
import com.pplbo.ecommerce.productservice.model.Inventory;
import com.pplbo.ecommerce.productservice.model.ProductCategories;
import com.pplbo.ecommerce.productservice.repository.BrandRepository;
import com.pplbo.ecommerce.productservice.repository.CategoryRepository;
import com.pplbo.ecommerce.productservice.repository.ProductCategoriesRepository;
import com.pplbo.ecommerce.productservice.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCategoriesRepository productCategoriesRepository;

    private Brand brand;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        brand = brandRepository.save(Brand.builder().name("Test Brand").logo("Test Logo").description("Test Description").build());
        productRequest = new ProductRequest("Ban Motor", 10000L, "Ban untuk motor", brand.getId(), "www.image.com", 50);
    }

    @Test
    void testGetAllProducts() {
        productService.createProduct(productRequest);
        List<ProductResponse> allProducts = productService.getAllProducts();
        assertFalse(allProducts.isEmpty());
    }

    @Test
    void testGetProductById() {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        ProductResponse foundProduct = productService.getProductById(createdProduct.id());
        assertNotNull(foundProduct);
        assertEquals(createdProduct.id(), foundProduct.id());
    }

    @Test
    void testGetCategoryOfProduct() {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        Category category = categoryRepository.save(new Category(1, "Test Category"));
        productCategoriesRepository.save(ProductCategories.builder().productId(createdProduct.id()).categoryId(category.getCategoryId()).build());
        
        ProductCategoriesResponse categoryResponse = productService.getCategoryOfProduct(createdProduct.id());
        assertFalse(categoryResponse.categoryName().isEmpty());
        assertTrue(categoryResponse.categoryName().contains("Test Category"));
    }

    @Test
    void testGetAllProductDetail() {
        productService.createProduct(productRequest);
        List<ProductDetailResponse> allProductDetails = productService.getAllProductDetail();
        assertFalse(allProductDetails.isEmpty());
    }

    @Test
    void testCreateProduct() {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        assertNotNull(createdProduct);
        assertEquals(productRequest.name(), createdProduct.name());
    }

    @Test
    void testCreateProductAlreadyExist() {
        productService.createProduct(productRequest);
        assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(productRequest);
        });
    }

    @Test
    void testUpdateProduct() {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        ProductRequest updatedRequest = new ProductRequest("Ban Mobil", 1250000L, "Ban untuk mobil", brand.getId(), "www.updatedimage.com", 100);

        ProductResponse updatedProduct = productService.updateProduct(createdProduct.id(), updatedRequest);

        assertNotNull(updatedProduct);
        assertEquals(updatedRequest.name(), updatedProduct.name());
        assertEquals(updatedRequest.price(), updatedProduct.price());
        assertEquals(updatedRequest.description(), updatedProduct.description());
        assertEquals(updatedRequest.brandId(), updatedProduct.brandId());
        assertEquals(updatedRequest.image(), updatedProduct.image());
    }

    @Test
    void testDeleteProduct() {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        productService.deleteProduct(createdProduct.id());
        assertNull(productRepository.findById(createdProduct.id()).orElse(null));
    }
}
