package com.pplbo.ecommerce.paymentservice.dto;

public record UserPaymentResponse(Long paymentId, Long userId, String paymentMethod, String balance) {
}
