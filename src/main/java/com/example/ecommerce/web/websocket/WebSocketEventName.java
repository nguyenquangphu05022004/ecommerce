package com.example.ecommerce.web.websocket;

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
