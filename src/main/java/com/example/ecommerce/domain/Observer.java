package com.example.ecommerce.domain;

import com.example.ecommerce.repository.NotificationRepository;

public interface Observer {
    void notify(NotificationRepository notificationRepository);
}
