package com.pplbo.ecommerce.paymentservice.service;

import com.pplbo.ecommerce.paymentservice.dto.UserPaymentRequest;
import com.pplbo.ecommerce.paymentservice.dto.UserPaymentResponse;
import com.pplbo.ecommerce.paymentservice.model.UserPayment;
import com.pplbo.ecommerce.paymentservice.repository.UserPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    public List<UserPaymentResponse> getAllUserPayments() {
        return userPaymentRepository.findAll().stream()
                .map(this::toUserPaymentResponse)
                .collect(Collectors.toList());
    }

    public Optional<UserPaymentResponse> getUserPaymentById(Long paymentId) {
        return userPaymentRepository.findById(paymentId)
                .map(this::toUserPaymentResponse);
    }

    public UserPaymentResponse createUserPayment(UserPaymentRequest userPaymentRequest) {
        UserPayment userPayment = toUserPayment(userPaymentRequest);
        userPayment = userPaymentRepository.save(userPayment);
        return toUserPaymentResponse(userPayment);
    }

    public UserPaymentResponse updateUserPayment(Long paymentId, UserPaymentRequest userPaymentRequest) {
        UserPayment userPayment = toUserPayment(userPaymentRequest);
        userPayment.setPaymentId(paymentId);
        userPayment = userPaymentRepository.save(userPayment);
        return toUserPaymentResponse(userPayment);
    }

    public void deleteUserPayment(Long paymentId) {
        userPaymentRepository.deleteById(paymentId);
    }

    private UserPayment toUserPayment(UserPaymentRequest userPaymentRequest) {
        return UserPayment.builder()
                .paymentId(userPaymentRequest.paymentId())
                .userId(userPaymentRequest.userId())
                .paymentMethod(userPaymentRequest.paymentMethod())
                .balance(userPaymentRequest.balance())
                .build();
    }

    private UserPaymentResponse toUserPaymentResponse(UserPayment userPayment) {
        return new UserPaymentResponse(
                userPayment.getPaymentId(),
                userPayment.getUserId(),
                userPayment.getPaymentMethod(),
                userPayment.getBalance());
    }
}
