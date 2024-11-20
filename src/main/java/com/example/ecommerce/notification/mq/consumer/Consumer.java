package com.example.ecommerce.notification.mq.consumer;

import com.example.ecommerce.notification.mq.message.app.AppMessage;
import com.example.ecommerce.notification.mq.message.email.EmailMessage;
import com.example.ecommerce.notification.AppNotificationDetails;
import com.example.ecommerce.notification.AppNotificationDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ApplicationContext applicationContext;

    @EventListener
    @Async
    public void consumerAppMessage(AppMessage appMessage) {
        SimpMessagingTemplate template = this.applicationContext.getBean(SimpMessagingTemplate.class);
        AppNotificationDetailsRepository repository = this.applicationContext.getBean(AppNotificationDetailsRepository.class);

        repository.save(new AppNotificationDetails(
                appMessage.getFromUserId(),
                appMessage.getToUserId(),
                appMessage.getMessage()
        ));

        template.convertAndSend("/topic/notification/user/" + appMessage.getToUserId(), appMessage);
    }

    @Async
    @EventListener
    public void consumer(EmailMessage emailMessage) {
        MailSender mailSender = this.applicationContext.getBean(MailSender.class);
        //sendMail
    }
}
