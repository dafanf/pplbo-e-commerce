package com.pplbo.ecommerce.paymentservice.dto;

import java.util.Date;

public record PaymentRequest(Long paymentId, Long orderId, String paymentMethod, Double paymentAmount,
                String paymentStatus, Date paymentDate) {

}
