package com.example.ecommerce.web.websocket;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WebSocketMessage {
    private WebSocketEventName eventName;
    private String username;
    private LocalDateTime time;
    private boolean isOnline;

    public WebSocketMessage(WebSocketEventName eventName, String username, boolean isOnline) {
        this.eventName = eventName;
        this.username = username;
        this.isOnline = isOnline;
        this.time = LocalDateTime.now();
    }
}
