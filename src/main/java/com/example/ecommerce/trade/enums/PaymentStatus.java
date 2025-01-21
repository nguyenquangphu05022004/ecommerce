package com.example.ecommerce.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    SUCCESS("Đã thanh toán"),
    PROCESSING("Chưa thanh toán");
    private final String value;

}
