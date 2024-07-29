package com.example.ecommerce.domain.entities.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Payment {
    PAY_AT_HOME("Thanh toán tại nhà"),
    PAY_BY_BANK("Thanh toán qua ngân hàng");
    @Getter
    private final String name;
}
