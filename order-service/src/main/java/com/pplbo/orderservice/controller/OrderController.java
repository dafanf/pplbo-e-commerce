package com.pplbo.orderservice.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pplbo.orderservice.dto.OrderRequest;
import com.pplbo.orderservice.dto.OrderResponse;
import com.pplbo.orderservice.event.domain.OrderCreateEvent;
import com.pplbo.orderservice.service.OrderService;
import com.pplbo.orderservice.model.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Optional<OrderResponse> order = orderService.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @PostMapping
    // public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {

    // return orderService.save(orderRequest);
    // }

    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {

        OrderResponse order = orderService.save(orderRequest);
        OrderCreateEvent event = new OrderCreateEvent(order);
        orderService.createEventOrder(event);
        return "Order Created Event sent to Kafka topic";
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        Optional<OrderResponse> orderOptional = orderService.findById(id);
        if (orderOptional.isPresent()) {
            OrderResponse updatedOrder = orderService.save(orderRequest);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id).isPresent()) {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
