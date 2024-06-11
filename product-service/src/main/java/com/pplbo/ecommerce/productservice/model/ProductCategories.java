package com.pplbo.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer productId;

    private Integer categoryId;
}