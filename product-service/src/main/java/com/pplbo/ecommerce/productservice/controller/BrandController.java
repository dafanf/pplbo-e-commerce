package com.pplbo.ecommerce.productservice.controller;

import com.pplbo.ecommerce.productservice.dto.BrandRequest;
import com.pplbo.ecommerce.productservice.model.Brand;
import com.pplbo.ecommerce.productservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand createBrand(@RequestBody BrandRequest brandRequest){
        return brandService.createBrand(brandRequest);
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable("id") Integer id){
        return brandService.getBrandById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBrand(@PathVariable("id") Integer id){
        brandService.removeBrand(id);
    }

    //change brand name
    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable Integer id, @RequestBody BrandRequest brandRequest){
        return brandService.updateBrand(id, brandRequest);
    }

    @GetMapping("/all")
    public List<Brand> getAllBrands(){
        return brandService.getAllBrands();
    }
}