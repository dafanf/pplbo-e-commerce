package com.pplbo.ecommerce.paymentservice.service;

import com.pplbo.ecommerce.paymentservice.model.Payment;
import com.pplbo.ecommerce.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public boolean isPaymentExist(Long id)
    {
        return paymentRepository.existsById(id);
    }
    // public Payment createPayment(Payment payment) {
    // return paymentRepository.save(payment);
    // }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Method to create a new payment
    public Payment createPayment(Payment payment) {
        Payment createdPayment = paymentRepository.save(payment);

        // Validate and update payment status after creating the payment
        validateAndUpdatePaymentStatus(createdPayment.getPaymentId());

        return createdPayment;
    }

    // Method to update an existing payment
    public Payment updatePayment(Payment payment) {
        Payment updatedPayment = paymentRepository.save(payment);

        // Validate and update payment status after updating the payment
        validateAndUpdatePaymentStatus(updatedPayment.getPaymentId());

        return updatedPayment;
    }

    public Payment validateAndUpdatePaymentStatus(Long paymentId) {
        // Retrieve the payment from the repository
        Payment payment = paymentRepository.findById(paymentId).orElse(null);

        // Check if the payment exists
        if (payment == null) {
            // Handle case where payment does not exist
            return null;
        }

        // Perform validation based on certain criteria
        // For example, let's assume payment is valid if the payment amount is greater
        // than 0
        if (payment.getPaymentAmount() > 500) {
            // Update payment status to "Paid"
            payment.setPaymentStatus("Paid");
        } else {
            // Update payment status to "Rejected"
            payment.setPaymentStatus("Rejected");
        }

        // Save the updated payment
        return paymentRepository.save(payment);
    }
}