package com.pplbo.ecommerce.paymentservice.dto;

import java.util.Date;
import java.util.List;

public record PaymentForm(Long orderId, Double orderAmount, Date orderDate, String chosenPaymentMethod) {
}
