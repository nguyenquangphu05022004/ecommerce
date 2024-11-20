package com.example.ecommerce.domain.notification;

import com.example.ecommerce.domain.entities.Order;
import com.example.ecommerce.repository.NotificationRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Entity
@Table(name = "order_notifications")
@DiscriminatorValue("ORDER")
public class OrderNotification extends Notification{
    private Order order;

    @Override
    public void send(NotificationRepository notificationRepository,
                     SimpMessagingTemplate messagingTemplate) {

    }
}
