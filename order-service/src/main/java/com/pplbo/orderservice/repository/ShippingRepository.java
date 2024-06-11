package com.pplbo.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pplbo.orderservice.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long>{
    
}
