package com.example.ecommerce.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EntityType {
    PRODUCT("product"),
    EVALUATION("evaluation"),
    CATEGORY("category"),
    USER("avatar"),
    ORDER("order"),
    CHAT_MESSAGE("chat message");
    @Getter
    private final String type;

}
