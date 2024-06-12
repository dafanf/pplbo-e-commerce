package com.pplbo.ecommerce.productservice.model;

import jakarta.persistence.*;
import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer categoryId;

    @Column(name = "name", nullable = false)
    private String categoryName;

    @Column(name = "description", nullable = false)
    private String categoryDescription;
}
