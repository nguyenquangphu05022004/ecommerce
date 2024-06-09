package com.example.ecommerce.dto;

import com.example.ecommerce.domain.Payment;
import com.example.ecommerce.domain.UserContactDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private UserContactDetails contactDetails;
    private Long stockId;
    private Integer quantity;
    private Payment payment;
    private Integer couponPercent;
}
