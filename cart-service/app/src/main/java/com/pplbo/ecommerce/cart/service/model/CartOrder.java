package com.pplbo.ecommerce.cart.service.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CartOrder {
    private Date orderDate;
    private String orderStatus;
    private Long totalPrice;
    private List<LineItem> lineItems;
    private Shipping shipping;
    private Long customerId;
    private Long paymentId;
}
