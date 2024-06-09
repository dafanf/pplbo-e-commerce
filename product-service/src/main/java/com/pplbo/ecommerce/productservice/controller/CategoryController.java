package com.pplbo.ecommerce.productservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.pplbo.ecommerce.productservice.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import com.pplbo.ecommerce.productservice.model.Category;
import com.pplbo.ecommerce.productservice.dto.CategoryRequest;
import com.pplbo.ecommerce.productservice.dto.CategoryResponse;

import java.util.List;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/products/category")
@RequiredArgsConstructor

public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping("/allcategory")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable("id") int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.removeCategory(categoryRequest.categoryName());
    }
}