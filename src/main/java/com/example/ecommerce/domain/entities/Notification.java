package com.example.ecommerce.domain.entities;

import com.example.ecommerce.service.event.listener.NotificationActionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity {
    private String message;
    @Embedded
    private EntityType entityType;
    private NotificationActionType notificationActionType;
}
