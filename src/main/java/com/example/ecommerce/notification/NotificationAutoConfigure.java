package com.example.ecommerce.notification;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationAutoConfigure {
    @Bean
    public NotificationEventManager notificationEventManager(
            @Qualifier("appNotification") AppNotificationService appNotificationService,
            @Qualifier("emailNotification") EmailNotificationService emailNotificationService
    ) {
        NotificationEventManager manager = new NotificationEventManager();
        manager.addNotificationEvent(appNotificationService);
        manager.addNotificationEvent(emailNotificationService);
        return manager;
    }
}
