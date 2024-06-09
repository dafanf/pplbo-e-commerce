package com.pplbo.ecommerce.paymentservice.service;

import com.pplbo.ecommerce.paymentservice.dto.PaymentRequest;
import com.pplbo.ecommerce.paymentservice.dto.PaymentResponse;
import com.pplbo.ecommerce.paymentservice.model.Payment;
import com.pplbo.ecommerce.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

        @Autowired
        private PaymentRepository paymentRepository;

        public List<PaymentResponse> getAllPayments() {
                return paymentRepository.findAll().stream()
                                .map(this::toPaymentResponse)
                                .collect(Collectors.toList());
        }

        public Optional<PaymentResponse> getPaymentById(Long paymentId) {
                return paymentRepository.findById(paymentId)
                                .map(this::toPaymentResponse);
        }

        public PaymentResponse createPayment(PaymentRequest paymentRequest) {
                Payment payment = toPayment(paymentRequest);
                return toPaymentResponse(paymentRepository.save(payment));
        }

        public PaymentResponse updatePayment(Long paymentId, PaymentRequest paymentRequest) {
                Payment payment = toPayment(paymentRequest);
                payment.setPaymentId(paymentId);
                return toPaymentResponse(paymentRepository.save(payment));
        }

        public void deletePayment(Long paymentId) {
                paymentRepository.deleteById(paymentId);
        }

        private Payment toPayment(PaymentRequest paymentRequest) {
                return Payment.builder()
                                .paymentId(paymentRequest.paymentId())
                                .orderId(paymentRequest.orderId())
                                .paymentMethod(paymentRequest.paymentMethod())
                                .paymentDate(paymentRequest.paymentDate())
                                .paymentStatus(paymentRequest.paymentStatus())
                                .paymentAmount(paymentRequest.paymentAmount())
                                .build();
        }

        private PaymentResponse toPaymentResponse(Payment payment) {
                return new PaymentResponse(
                                payment.getPaymentId(),
                                payment.getOrderId(),
                                payment.getPaymentMethod(),
                                payment.getPaymentAmount(),
                                payment.getPaymentStatus(),
                                payment.getPaymentDate());
        }
}
