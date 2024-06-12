package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.ENUM.Payment;
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
    private Long couponId;
}
