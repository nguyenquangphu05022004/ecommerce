package com.example.ecommerce.notification.mq.message.app;

import lombok.Data;

@Data
public class AppMessage {
    private String message;
    private Long fromUserId;
    private Long toUserId;
}
