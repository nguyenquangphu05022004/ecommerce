package com.example.ecommerce.domain.notification;

import com.example.ecommerce.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public interface NotificationTemplate {
    void notify(NotificationRepository notificationRepository,
                SimpMessagingTemplate messagingTemplate);
    void send(NotificationRepository notificationRepository,
              SimpMessagingTemplate messagingTemplate);
}
