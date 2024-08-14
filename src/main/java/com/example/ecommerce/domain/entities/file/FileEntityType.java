package com.example.ecommerce.domain.entities.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FileEntityType {
    PRODUCT_INVENTORY,
    EVALUATION,
    CATEGORY,
    USER,
    ORDER,
    CHAT_MESSAGE,
    CHAT_MESSAGE_CONVERSATION;

}
