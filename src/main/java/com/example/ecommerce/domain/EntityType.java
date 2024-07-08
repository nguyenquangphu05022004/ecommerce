package com.example.ecommerce.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EntityType {
    PRODUCT("product"),
    EVALUATION("evaluation"),
    CATEGORY("category"),
    USER("avatar");
    @Getter
    private final String type;

}
