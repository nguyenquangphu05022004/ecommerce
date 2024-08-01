package com.example.ecommerce.domain.entities.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FileEntityType {
    PRODUCT("product"),
    EVALUATION("evaluation"),
    CATEGORY("category"),
    USER("avatar"),
    ORDER("order"),
    CHAT_MESSAGE("chat message");
    @Getter
    private final String type;

}
