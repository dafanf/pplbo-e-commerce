package com.pplbo.ecommerce.paymentservice.service;

import com.pplbo.ecommerce.paymentservice.dto.TransactionRequest;
import com.pplbo.ecommerce.paymentservice.dto.TransactionResponse;
import com.pplbo.ecommerce.paymentservice.model.Transaction;
import com.pplbo.ecommerce.paymentservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.pplbo.ecommerce.paymentservice.model.UserPayment;
import com.pplbo.ecommerce.paymentservice.repository.UserPaymentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.ecommerce.paymentservice.event.ValidatePayment;
import org.springframework.kafka.annotation.KafkaListener;
import com.pplbo.ecommerce.paymentservice.event.OrderPaymentEvent;
import java.util.Date;
import com.pplbo.ecommerce.paymentservice.dto.dtoorder.OrderResponse;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "payment";
    private static final String TOPIC_PUBLISH = "orderReplyEvent";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendPaymentEvent(ValidatePayment validatePayment) {
        try {
            String message = objectMapper.writeValueAsString(validatePayment);
            kafkaTemplate.send(TOPIC_PUBLISH, message);
            System.out.println("Produced message: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOrderPaymentEvent(OrderPaymentEvent orderPaymentEvent) {
        try {
            String message = objectMapper.writeValueAsString(orderPaymentEvent);
            kafkaTemplate.send(TOPIC_PUBLISH, message);
            System.out.println("Produced message: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "paymentRequestEvent", groupId = "group_id")
    public void consumePaymentRequestEvent(String message) {
        try {
            OrderPaymentEvent orderPaymentEvent = objectMapper.readValue(message, OrderPaymentEvent.class);
            handlePaymentRequestEvent(orderPaymentEvent);
            System.out.println("Consumed message: " + message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePaymentRequestEvent(OrderPaymentEvent orderPaymentEvent) {
        // Handle the payment request event
        Long paymentId = orderPaymentEvent.getOrder().paymentId();

        // Fetch the UserPayment
        UserPayment userPayment = userPaymentRepository.findById(paymentId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid paymentId: " + paymentId));

        // Check if the balance is sufficient
        double balance = Double.parseDouble(userPayment.getBalance());
        String transactionStatus; 
        OrderResponse test;
        if (balance > orderPaymentEvent.getOrder().totalPrice()) {
            transactionStatus = "PESANAN_SELESAI";
            // Update Order status
            test = orderPaymentEvent.getOrder().withOrderStatus("PESANAN_SELESAI");
            balance -= orderPaymentEvent.getOrder().totalPrice();
        } else {
            transactionStatus = "PESANAN_DIBATALKAN";
            // Update Order status
            test = orderPaymentEvent.getOrder().withOrderStatus("PESANAN_DIBATALKAN");
        }

        // Update the UserPayment balance
        userPayment.setBalance(String.valueOf(balance));
        userPaymentRepository.save(userPayment);

        // Create the transaction
        Transaction transaction = Transaction.builder()
                .paymentId(paymentId)
                .orderId(orderPaymentEvent.getOrder().orderId())
                .transactionDate(new Date())
                .transactionAmount(orderPaymentEvent.getOrder().totalPrice())
                .transactionStatus(transactionStatus)
                .build();

        transactionRepository.save(transaction);

        System.out.println("INI TEST WOY : " + test);
        // Send the payment event
        sendOrderPaymentEvent(new OrderPaymentEvent(test));
        // sendOrderPaymentEvent(orderPaymentEvent);
    }

    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::toTransactionResponse)
                .collect(Collectors.toList());
    }

    public Optional<TransactionResponse> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(this::toTransactionResponse);
    }

    // @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        // Fetch the UserPayment
        UserPayment userPayment = userPaymentRepository.findById(transactionRequest.paymentId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid paymentId: " + transactionRequest.paymentId()));

        // Check if the balance is sufficient
        double balance = Double.parseDouble(userPayment.getBalance());
        String transactionStatus;
        if (balance > transactionRequest.transactionAmount()) {
            transactionStatus = "PAYMENT_SUCCESS";
            balance -= transactionRequest.transactionAmount();
        } else {
            transactionStatus = "PAYMENT_FAILED";
        }

        // Update the UserPayment balance
        userPayment.setBalance(String.valueOf(balance));
        userPaymentRepository.save(userPayment);

        // Create the transaction
        Transaction transaction = toTransaction(transactionRequest);
        transaction.setTransactionStatus(transactionStatus);
        transaction = transactionRepository.save(transaction);

        // Send the payment event
        sendPaymentEvent(new ValidatePayment(transaction));

        return toTransactionResponse(transaction);
    }

    public TransactionResponse updateTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Transaction transaction = toTransaction(transactionRequest);
        transaction.setTransactionId(transactionId);
        transaction = transactionRepository.save(transaction);
        return toTransactionResponse(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    private Transaction toTransaction(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .transactionId(transactionRequest.transactionId())
                .paymentId(transactionRequest.paymentId())
                .orderId(transactionRequest.orderId())
                .transactionDate(transactionRequest.transactionDate())
                .transactionAmount(transactionRequest.transactionAmount())
                .transactionStatus(transactionRequest.transactionStatus())
                .build();
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getPaymentId(),
                transaction.getOrderId(),
                transaction.getTransactionDate(),
                transaction.getTransactionAmount(),
                transaction.getTransactionStatus());
    }
}