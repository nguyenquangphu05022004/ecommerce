package com.example.ecommerce.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CustomStatusCode {
    NOT_FOUND(1000),
    CODE_EXPIRED(1001),
    SUCCESS(1111);
    @Getter
    private final int number;
}
