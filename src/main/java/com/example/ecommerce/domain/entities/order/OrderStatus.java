package com.example.ecommerce.domain.entities.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    ALL("Tất cả"),
    SUCCESS("Thành công"),
    PROCESSING("Đang tiến hành"),
    NOT_APPROVAL("Chưa nhận đơn");
    @Getter
    private final String value;
}
