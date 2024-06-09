package com.pplbo.ecommerce.productservice.dto.dtoorder;

import java.util.Date;
import java.util.List;

public record OrderResponse(
    Long orderId,
    Date orderDate,
    String orderStatus,
    Double totalPrice,
    List<OrderLineItemResponse> orderLineItems,
    ShippingResponse shipping,
    CustomerResponse customer,
    Long paymentId
) {}
