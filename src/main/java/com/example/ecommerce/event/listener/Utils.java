package com.example.ecommerce.event.listener;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.Notification;
import com.example.ecommerce.domain.model.binding.EmailDetails;
import com.example.ecommerce.event.Event;

import static com.example.ecommerce.event.Event.EventType.SEND_MAIL;

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
