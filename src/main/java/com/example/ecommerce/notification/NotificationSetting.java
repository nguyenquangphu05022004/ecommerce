package com.example.ecommerce.notification;


import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Setting notification of user
 */
@Data
@Table(name = "notification_notification_setting")
@Entity
@NoArgsConstructor
public class NotificationSetting extends BaseEntity {
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
