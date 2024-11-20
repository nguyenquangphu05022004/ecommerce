package com.example.ecommerce.service.event.listener;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.notification.Notification;
import com.example.ecommerce.domain.model.binding.EmailDetails;
import com.example.ecommerce.service.event.Event;

import static com.example.ecommerce.service.event.Event.EventType.SEND_MAIL;

public class Utils {

    protected static void sendToEmail(Notification notification, String subject) {
        Event.getInstance().postEvent(SEND_MAIL,
                new EmailDetails(SecurityUtils.getUsername(),
                        subject,
                        notification.getMessage()));
    }
    protected static void sendToEmail(String recepient,
                                      Notification notification,
                                      String subject) {
        Event.getInstance().postEvent(SEND_MAIL,
                new EmailDetails(recepient,
                        subject,
                        notification.getMessage()));
    }

}
