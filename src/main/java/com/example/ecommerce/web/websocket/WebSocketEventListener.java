package com.example.ecommerce.web.websocket;

import com.example.ecommerce.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static com.example.ecommerce.web.websocket.WebSocketEventName.CONNECT;
import static com.example.ecommerce.web.websocket.WebSocketEventName.DISCONNECT;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final IUserService userService;
    private final SimpMessagingTemplate template;

    @EventListener
    public void handleSessionConnected(SessionConnectEvent sessionConnectEvent) {
        handleSession(sessionConnectEvent.getMessage(), CONNECT, true);
    }

    @EventListener
    public void handleSessionDisConnected(SessionDisconnectEvent sessionDisconnectEvent) {
        handleSession(sessionDisconnectEvent.getMessage(), DISCONNECT, false);
    }

    private void handleSession(Message message,WebSocketEventName event, boolean isOnline) {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);
        String username = accessor.getUser().getName();
        userService.updateOnlineStatus(username, isOnline);
        WebSocketMessage webSocketMessage = new WebSocketMessage(
                event,
                username,
                isOnline
        );

        template.convertAndSend(event.getDestination(), webSocketMessage);
    }
}
