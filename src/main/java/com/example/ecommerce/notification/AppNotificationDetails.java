package com.example.ecommerce.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
@Entity
@Table(name = "notification_app_notifications")
public class AppNotificationDetails {
    @Id
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String message;


    public AppNotificationDetails(Long fromUserId, Long toUserId, String message) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message = message;
    }
}
