package com.example.ecommerce.notification;


import com.example.ecommerce.domain.entities.User;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import lombok.Data;

/**
 * Setting notification of user
 */
@Data
public class NotificationSetting {
    /**
     * @link: User#getId()
     */
    private Long userId;
    private NotificationTypeEnum notificationTypeEnum;
    private boolean enable;

    public NotificationSetting(Long userId, NotificationTypeEnum notificationTypeEnum, boolean enable) {
        this.userId = userId;
        this.notificationTypeEnum = notificationTypeEnum;
        this.enable = enable;
    }

    public static enum NotificationTypeEnum {
        APP,
        EMAIL
    }
}
