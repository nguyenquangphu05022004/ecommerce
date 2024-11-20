package com.example.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.notification.NotificationSetting.*;

@Service
@RequiredArgsConstructor
public class NotificationSettingServiceImpl implements NotificationSettingService{
    private final NotificationSettingRepository notificationSettingRepository;
    @Override
    public void update(NotificationTypeEnum notificationTypeEnum, Long userId, boolean enable) {
        NotificationSetting notificationSetting = new NotificationSetting(
                userId,
                notificationTypeEnum,
                enable
        );
        this.notificationSettingRepository.save(notificationSetting);
    }

    @Override
    public boolean isEnable(NotificationTypeEnum notificationTypeEnum, Long userId) {
        NotificationSetting notificationSetting = this.notificationSettingRepository.findByUserIdAndNotificationTypeEnum(
                userId,
                notificationTypeEnum
        );
        return notificationSetting.isEnable();
    }


    @Override
    public List<NotificationSetting> getListByUserId(Long userId) {
        return this.notificationSettingRepository.findAllByUserId(userId);
    }
}
