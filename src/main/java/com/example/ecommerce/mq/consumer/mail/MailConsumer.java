package com.example.ecommerce.mq.consumer.mail;

import com.example.ecommerce.mq.message.mail.MailMessage;
import com.example.ecommerce.system.service.mail.MailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailConsumer {
    private final MailSendService mailSendService;

    @Async
    @EventListener
    public void consumeMailMessage(MailMessage mailMessage) throws Exception {
        this.mailSendService.doSendMail(mailMessage);
    }
}
