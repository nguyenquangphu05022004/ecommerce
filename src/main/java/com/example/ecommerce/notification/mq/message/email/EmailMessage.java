package com.example.ecommerce.notification.mq.message.email;

import lombok.Data;

@Data
public class EmailMessage {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
}
