package com.example.ecommerce.mq.message.mail;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
public class MailMessage {
    private String fromMail;
    private String toMail;
    private Long mailLogId;
}
