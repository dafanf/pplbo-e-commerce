package com.pplbo.ecommerce.productservice.service;

import com.pplbo.ecommerce.productservice.dto.BrandRequest;
import com.pplbo.ecommerce.productservice.model.Brand;
import com.pplbo.ecommerce.productservice.repository.BrandRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand createBrand(BrandRequest brandRequest){
        Brand brand = Brand.builder()
                .name(brandRequest.name())
                .build();
        brandRepository.save(brand);

        return brand;
    }
}
