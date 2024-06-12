package com.pplbo.ecommerce.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Entity
@Table(name = "user_payment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPayment {
    @Id
    private Long paymentId;
    private Long userId; // Assuming userId is a part of the payment details
    private String paymentMethod;
    private String balance;
}
