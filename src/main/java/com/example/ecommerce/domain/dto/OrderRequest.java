package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.Payment;
import com.example.ecommerce.domain.UserContactDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private UserContactDetails userContactDetails;
    private Long stockId;
    private Integer quantity;
    private Payment payment;
    private Long couponId;
    private Long stockClassificationId;
}
