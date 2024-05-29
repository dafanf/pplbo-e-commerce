package com.pplbo.orderservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pplbo.orderservice.dto.OrderRequest;
import com.pplbo.orderservice.dto.OrderResponse;
import com.pplbo.orderservice.dto.OrderLineItemResponse;
import com.pplbo.orderservice.dto.OrderLineItemRequest;
import com.pplbo.orderservice.dto.ShippingResponse;
import com.pplbo.orderservice.dto.ShippingRequest;
import com.pplbo.orderservice.dto.CustomerResponse;
import com.pplbo.orderservice.dto.CustomerRequest;
import com.pplbo.orderservice.model.Order;
import com.pplbo.orderservice.model.OrderLineItem;
import com.pplbo.orderservice.model.Shipping;
import com.pplbo.orderservice.model.Customer;
import com.pplbo.orderservice.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<OrderResponse> findById(Long id) {
        return orderRepository.findById(id).map(this::convertToDto);
    }

    public OrderResponse save(OrderRequest orderRequest) {
        Order order = convertToEntity(orderRequest);
        return convertToDto(orderRepository.save(order));
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    //olah Response
    private OrderResponse convertToDto(Order order) {
        List<OrderLineItemResponse> orderLineItems = order.getOrderLineItems().stream()
            .map(item -> new OrderLineItemResponse(item.getOrderLineItemId(), item.getQuantity(), item.getProductId()))
            .collect(Collectors.toList());

        Shipping shipping = order.getShipping();
        ShippingResponse shippingResponse = new ShippingResponse(
            shipping.getShippingId(),
            shipping.getShippingName(),
            shipping.getShippingPrice(),
            shipping.getShippingStatus(),
            shipping.getShippingAddress()
        );

        Customer customer = order.getCustomer();
        CustomerResponse customerResponse = new CustomerResponse(
            customer.getCustomerId(),
            customer.getNama()
        );

        return new OrderResponse(
            order.getOrderId(),
            order.getOrderDate(),
            order.getOrderStatus(),
            order.getTotalPrice(),
            orderLineItems,
            shippingResponse,
            customerResponse
        );
    }

    //olah Request
    private Order convertToEntity(OrderRequest orderRequest) {
        List<OrderLineItem> orderLineItems = orderRequest.orderLineItems().stream()
            .map(item -> new OrderLineItem(null, item.quantity(), item.productId(), null))
            .collect(Collectors.toList());
    
        ShippingRequest shippingRequest = orderRequest.shipping();
        Shipping shipping = new Shipping(
            null, //id shipping set null (karna auto increment di DB)
            shippingRequest.shippingName(),
            shippingRequest.shippingPrice(),
            shippingRequest.shippingStatus(),
            shippingRequest.shippingAddress()
        );
    
        // Buat objek Customer berdasarkan nama pelanggan dari request body
        Customer customer = new Customer(
            null, //id customer set null (karna auto increment di DB)
            orderRequest.customerName() 
        );
    
        Order order = new Order(
            null, //id order set null (karna auto increment di DB)
            orderRequest.orderDate(),
            orderRequest.orderStatus(),
            orderRequest.totalPrice(),
            orderLineItems,
            shipping,
            customer
        );
    
        orderLineItems.forEach(item -> item.setOrder(order));
        return order;
    }
}
