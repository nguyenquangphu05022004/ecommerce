package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.UserContactDetails;
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
