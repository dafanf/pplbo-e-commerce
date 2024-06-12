package com.pplbo.ecommerce.productservice.service;

import com.pplbo.ecommerce.productservice.dto.BrandRequest;
import com.pplbo.ecommerce.productservice.model.Brand;
import com.pplbo.ecommerce.productservice.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    private BrandRequest brandRequest;

    @BeforeEach
    void setUp() {
        brandRequest = new BrandRequest("Nestle", "https://www.nestle.co.id/themes/custom/da_vinci_code/logo.svg", "categories");
    }

    @Test
    void testCreateBrand() {
        Brand createdBrand = brandService.createBrand(brandRequest);

        assertNotNull(createdBrand);
        assertEquals(brandRequest.name(), createdBrand.getName());
        assertEquals(brandRequest.logo(), createdBrand.getLogo());
    }

    @Test
    void testCreateBrandAlreadyExist() {
        brandService.createBrand(brandRequest);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            brandService.createBrand(brandRequest);
        });
        

        assertEquals("Brand already exist", exception.getMessage());
    }

    @Test
    void testGetBrandById() {
        Brand createdBrand = brandService.createBrand(brandRequest);
        Brand foundBrand = brandService.getBrandById(createdBrand.getId());

        assertNotNull(foundBrand);
        assertEquals(createdBrand.getId(), foundBrand.getId());
    }

    @Test
    void testRemoveBrand() {
        Brand createdBrand = brandService.createBrand(brandRequest);
        brandService.removeBrand(createdBrand.getId());

        Optional<Brand> deletedBrand = brandRepository.findById(createdBrand.getId());
        assertFalse(deletedBrand.isPresent());
    }

    @Test
    void testUpdateBrand() {
        Brand createdBrand = brandService.createBrand(brandRequest);
        BrandRequest updatedRequest = new BrandRequest("Univeler", "https://www.nestle.co.id/themes/custom/da_vinci_code/logo.svg", "cate");

        Brand updatedBrand = brandService.updateBrand(createdBrand.getId(), updatedRequest);

        assertNotNull(updatedBrand);
        assertEquals(updatedRequest.name(), updatedBrand.getName());
        assertEquals(updatedRequest.logo(), updatedBrand.getLogo());
    }

    @Test
    void testGetAllBrands() {
        brandService.createBrand(brandRequest);
        List<Brand> allBrands = brandService.getAllBrands();

        assertNotNull(allBrands);
        assertFalse(allBrands.isEmpty());
    }
}
