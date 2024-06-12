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
import com.pplbo.ecommerce.productservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final InventoryService inventoryService;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .brandId(productRequest.brandId())
                .image(productRequest.image())
                .build();
        productRepository.save(product);
        Inventory inventory = Inventory.builder()
                .productName(product.getName())
                .productId(product.getId())
                .quantity(productRequest.stock())
                .build();

        inventoryService.addInventory(inventory);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getBrandId(), product.getImage());
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getBrandId(), product.getImage()))
                .collect(Collectors.toList());
    }

    public ProductResponse updateProduct(Integer id, ProductRequest productRequest){
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setDescription(productRequest.description());
        product.setBrandId(productRequest.brandId());
        product.setImage(productRequest.image());
        productRepository.save(product);

        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getBrandId(), product.getImage());
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    public ProductDetailResponse getProductById(Integer id){
        Product product = productRepository.findById(id).orElse(null);

        Brand brand = brandRepository.findById(product.getBrandId()).orElse(null);
        String brandName = brand != null ? brand.getName() : "Unknown";

        // Fetch ProductCategories
        List<ProductCategories> productCategories = productCategoriesRepository.findByProductId(product.getId());

        // Extract category IDs
        List<Integer> categoryIds = productCategories.stream()
                .map(ProductCategories::getCategoryId)
                .collect(Collectors.toList());

        // Fetch Categories
        List<Category> categories = categoryRepository.findByCategoryIdIn(categoryIds);

        // Extract category names
        List<String> categoryNames = categories.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());

        Inventory inventory = inventoryService.getInventoryByProductId(product.getId());

        return new ProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                brandName,
                product.getImage(),
                categoryNames,
                inventory.getQuantity()
        );
    }

    public ProductCategoriesResponse addCategoryToProduct(Integer id, ProductCategoriesRequest productCategoryRequest){
        List<String> categoryNames = new ArrayList<>();

        for (String categoryName : productCategoryRequest.categoryName()){
            Category category = categoryRepository.findByCategoryName(categoryName);

            if (category != null){
                ProductCategories productCategories = ProductCategories.builder()
                        .productId(id)
                        .categoryId(category.getCategoryId())
                        .build();
                productCategoriesRepository.save(productCategories);
                categoryNames.add(category.getCategoryName());
            }
            else{
                Category newCategory = Category.builder()
                        .categoryName(categoryName)
                        .build();
                categoryRepository.save(newCategory);

                ProductCategories productCategories = ProductCategories.builder()
                        .productId(id)
                        .categoryId(newCategory.getCategoryId())
                        .build();
                productCategoriesRepository.save(productCategories);

                categoryNames.add(newCategory.getCategoryName());
            }

        }

        return new ProductCategoriesResponse(id, categoryNames);
    }

    public ProductCategoriesResponse getCategoryOfProduct(Integer id){
        // Fetch ProductCategories
        List<ProductCategories> productCategories = productCategoriesRepository.findByProductId(id);

        // Extract category IDs
        List<Integer> categoryIds = productCategories.stream()
                .map(ProductCategories::getCategoryId)
                .collect(Collectors.toList());

        // Fetch Categories
        List<Category> categories = categoryRepository.findByCategoryIdIn(categoryIds);

        // Extract category names
        List<String> categoryNames = categories.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());

        // Create and return ProductCategoriesResponse
        return new ProductCategoriesResponse(id, categoryNames);
    }

    public List<ProductDetailResponse> getAllProductDetail(){
        List<Product> products = productRepository.findAll();

        List<ProductDetailResponse> productDetailResponses = new ArrayList<>();

        for(Product product : products){
            Brand brand = brandRepository.findById(product.getBrandId()).orElse(null);
            String brandName = brand != null ? brand.getName() : "Unknown";

            // Fetch ProductCategories
            List<ProductCategories> productCategories = productCategoriesRepository.findByProductId(product.getId());

            // Extract category IDs
            List<Integer> categoryIds = productCategories.stream()
                    .map(ProductCategories::getCategoryId)
                    .collect(Collectors.toList());

            // Fetch Categories
            List<Category> categories = categoryRepository.findByCategoryIdIn(categoryIds);

            // Extract category names
            List<String> categoryNames = categories.stream()
                    .map(Category::getCategoryName)
                    .collect(Collectors.toList());

            Inventory inventory = inventoryService.getInventoryByProductId(product.getId());

            ProductDetailResponse productDetailResponse = new ProductDetailResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    brandName,
                    product.getImage(),
                    categoryNames,
                    inventory.getQuantity()
            );

            productDetailResponses.add(productDetailResponse);
        }

        return productDetailResponses;
    }

}