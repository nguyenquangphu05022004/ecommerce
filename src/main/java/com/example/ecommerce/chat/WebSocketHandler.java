package com.example.ecommerce.chat;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info(SecurityUtils.username());
        // Notify other users about the new connection (optional)
        broadcastMessage("test" + ": has joined the chat.");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("test" + " has left the chat");
        // Notify other users about the disconnection (optional)
        broadcastMessage("test" + ": has left the chat.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload() + " from: " + "test");

        // Broadcast the message to all connected clients
        broadcastMessage("test" + ": " + message.getPayload());
    }

    private void broadcastMessage(String message) throws Exception {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
    }
}
