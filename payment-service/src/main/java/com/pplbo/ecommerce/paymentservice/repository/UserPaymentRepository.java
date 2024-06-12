package com.pplbo.ecommerce.paymentservice.repository;

import com.pplbo.ecommerce.paymentservice.model.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
    Optional<UserPayment> findByUserId(Long userId);
}
