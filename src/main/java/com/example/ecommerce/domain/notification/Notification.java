package com.example.ecommerce.domain.notification;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.repository.NotificationRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Entity
@Table(name = "notifications")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_notify")
public class Notification extends BaseEntity implements  NotificationTemplate{
    private String message;
    private User fromUser;

    public Notification(User fromUser) {
        this.fromUser = fromUser;
    }
    @Override
    public void notify(NotificationRepository notificationRepository, SimpMessagingTemplate messagingTemplate) {

    }

    @Override
    public void send(NotificationRepository notificationRepository, SimpMessagingTemplate messagingTemplate) {

    }

    public void setMessage(String message) {
        this.message = message;
    }
}
