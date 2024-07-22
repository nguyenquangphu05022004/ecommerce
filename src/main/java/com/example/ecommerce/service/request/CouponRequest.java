package com.example.ecommerce.service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CouponRequest {
    private LocalDateTime start;
    private LocalDateTime end;
    private int decreaseMoney;
}
