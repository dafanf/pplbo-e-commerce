package com.pplbo.ecommerce.productservice.service;

import lombok.RequiredArgsConstructor;
import com.pplbo.ecommerce.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.pplbo.ecommerce.productservice.dto.CategoryRequest;
import com.pplbo.ecommerce.productservice.dto.CategoryResponse;
import com.pplbo.ecommerce.productservice.model.Category;

import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
            .categoryName(categoryRequest.categoryName())
            .build();
        categoryRepository.save(category);
        return new CategoryResponse(category.getCategoryId(), category.getCategoryName());
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
        .stream()
        .map(category -> new CategoryResponse(category.getCategoryId(), category.getCategoryName()))
        .collect(Collectors.toList());
    }

    //Remove category by name
    public void removeCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        categoryRepository.delete(category);
    }
}
