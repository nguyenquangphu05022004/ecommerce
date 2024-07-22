package com.example.ecommerce.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouponResponse {
    private Long id;
    private String code;
    private int decreaseMoney;
}
