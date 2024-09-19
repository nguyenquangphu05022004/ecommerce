package com.example.ecommerce.domain.entities.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    DELIVERED("Da giao hang"),
    PROCESSING("Đang tiến hành"),
    SHIPPED("Dang van chuyen");
    @Getter
    private final String value;
}
