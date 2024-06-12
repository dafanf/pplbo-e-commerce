package com.pplbo.ecommerce.paymentservice.repository;

import com.pplbo.ecommerce.paymentservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPaymentId(Long paymentId);
}
