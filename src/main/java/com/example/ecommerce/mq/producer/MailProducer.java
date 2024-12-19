package com.example.ecommerce.mq.producer;

import com.example.ecommerce.mq.message.mail.MailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailProducer {
    private final ApplicationContext applicationContext;

    public void sendMail(String fromMail,String toMail, Long mailLogId) {
        MailMessage mailMessage = MailMessage.builder().toMail(toMail)
                .fromMail(fromMail).mailLogId(mailLogId).build();
        this.applicationContext.publishEvent(mailMessage);
    }
}
