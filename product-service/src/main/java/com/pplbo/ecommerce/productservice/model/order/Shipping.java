package com.pplbo.ecommerce.productservice.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "shippings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;
    
    private String shippingName;
    private Double shippingPrice;
    private String shippingStatus;
    private String shippingAddress;
}
