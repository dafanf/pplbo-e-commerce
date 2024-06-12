package com.pplbo.ecommerce.paymentservice.controller;

import com.pplbo.ecommerce.paymentservice.dto.UserPaymentRequest;
import com.pplbo.ecommerce.paymentservice.dto.UserPaymentResponse;
import com.pplbo.ecommerce.paymentservice.dto.PaymentForm;
import com.pplbo.ecommerce.paymentservice.event.ValidatePayment;
import com.pplbo.ecommerce.paymentservice.model.UserPayment;
import com.pplbo.ecommerce.paymentservice.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-payments")
public class UserPaymentController {

    @Autowired
    private UserPaymentService userPaymentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserPaymentResponse> getAllUserPayments() {
        return userPaymentService.getAllUserPayments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserPaymentResponse> getUserPaymentById(@PathVariable("id") Long id) {
        return userPaymentService.getUserPaymentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserPaymentResponse createUserPayment(@RequestBody UserPaymentRequest userPaymentRequest) {
        return userPaymentService.createUserPayment(userPaymentRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserPaymentResponse> updateUserPayment(@PathVariable("id") Long id,
            @RequestBody UserPaymentRequest userPaymentRequest) {
        Optional<UserPaymentResponse> existingPayment = userPaymentService.getUserPaymentById(id);

        if (existingPayment.isPresent()) {
            UserPaymentResponse updatedPayment = userPaymentService.updateUserPayment(id, userPaymentRequest);
            return ResponseEntity.ok(updatedPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUserPayment(@PathVariable("id") Long id) {
        userPaymentService.deleteUserPayment(id);
        return ResponseEntity.noContent().build();
    }
}
