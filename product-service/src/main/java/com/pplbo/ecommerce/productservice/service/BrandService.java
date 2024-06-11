package com.pplbo.ecommerce.productservice.service;

import com.pplbo.ecommerce.productservice.dto.BrandRequest;
import com.pplbo.ecommerce.productservice.model.Brand;
import com.pplbo.ecommerce.productservice.repository.BrandRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand createBrand(BrandRequest brandRequest){
        Brand brand = Brand.builder()
                .name(brandRequest.name())
                .logo(brandRequest.logo())
                .build();
        brandRepository.save(brand);

        return brand;
    }

    public Brand getBrandByName(String name){
        return brandRepository.findByName(name);
    }

    public Brand getBrandById(int id){
        return brandRepository.findById(id);
    }

    public void removeBrand(int id){
        brandRepository.deleteById(id);
    }

    public Brand updateBrand(int id, BrandRequest brandRequest){
        Brand brand = brandRepository.findById(id);
        brand.setName(brandRequest.name());
        brand.setLogo(brandRequest.logo());
        brandRepository.save(brand);

        return brand;
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }


}