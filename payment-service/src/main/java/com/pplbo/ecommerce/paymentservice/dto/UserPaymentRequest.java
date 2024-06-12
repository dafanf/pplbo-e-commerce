package com.pplbo.ecommerce.paymentservice.dto;

public record UserPaymentRequest(Long paymentId, Long userId, String paymentMethod, String balance) {
}
