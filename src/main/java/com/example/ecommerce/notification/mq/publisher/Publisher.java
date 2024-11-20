package com.example.ecommerce.notification.mq.publisher;

import com.example.ecommerce.notification.mq.message.app.AppMessage;
import com.example.ecommerce.notification.mq.message.email.EmailMessage;
import com.example.ecommerce.notification.NotificationSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Component;

import static com.example.ecommerce.notification.NotificationSetting.NotificationTypeEnum.*;

@RequiredArgsConstructor
@Component
public class Publisher {
    private final ApplicationContext applicationContext;
    private final NotificationSettingService notificationSettingService;

    public void notification(AppMessage appMessage) {
        if(this.notificationSettingService.isEnable(APP, appMessage.getFromUserId())) {
            applicationContext.publishEvent(appMessage);
        }
    }


    public void notification(Long toUserId, EmailMessage emailMessage) {
        MailSender mailSender = applicationContext.getBean(MailSender.class);
        if(this.notificationSettingService.isEnable(EMAIL, toUserId)) {
            applicationContext.publishEvent(emailMessage);
        }
    }
}
