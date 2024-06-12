package com.pplbo.ecommerce.paymentservice.dto;

import java.util.Date;

public record TransactionResponse(Long transactionId, Long paymentId, Long orderId, Date transactionDate,
        Double transactionAmount, String transactionStatus) {
}
