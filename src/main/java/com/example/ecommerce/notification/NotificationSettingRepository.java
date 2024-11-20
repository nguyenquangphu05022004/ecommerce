package com.example.ecommerce.notification;

import com.example.ecommerce.notification.NotificationSetting.NotificationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {
    NotificationSetting findByUserIdAndNotificationTypeEnum(
            Long userId,
            NotificationTypeEnum typeEnum
    );

    List<NotificationSetting> findAllByUserId(Long userId);
}
