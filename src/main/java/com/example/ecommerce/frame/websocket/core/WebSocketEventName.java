package com.example.ecommerce.frame.websocket.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WebSocketEventName {
    SUBSCRIBE(""),
    CONNECT("/chat/login"),
    DISCONNECT("/chat/logout"),
    MESSAGE("");

    @Getter
    private final String destination;
}
