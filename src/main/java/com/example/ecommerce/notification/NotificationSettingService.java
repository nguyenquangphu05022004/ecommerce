package com.example.ecommerce.notification;

import com.example.ecommerce.notification.NotificationSetting.NotificationTypeEnum;

import java.util.List;

public interface NotificationSettingService {
    /**
     * Enable or disable notification for user
     * @param notificationTypeEnum: type of notification
     * @param userId: user
     */
    void update(NotificationTypeEnum notificationTypeEnum, Long userId, boolean enable);

    /**
     * Check whether this notification type user is enabling or not
     * @param notificationTypeEnum: type of notification
     * @param userId: user
     * @return: boolean
     */
    boolean isEnable(NotificationTypeEnum notificationTypeEnum, Long userId);

    /**
     * Get list of notification show for user
     * @param userId: userId
     * @return: List type of notification
     */
    List<NotificationSetting> getListByUserId(Long userId);


    /**
     * Enable all notification type when user register account
     * @param userId
     */
    default void enableAll(Long userId) {
        update(NotificationTypeEnum.EMAIL, userId, true);
        update(NotificationTypeEnum.APP, userId, true);
    };

}
