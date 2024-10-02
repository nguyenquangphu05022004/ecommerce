package com.example.ecommerce.service.event.listener;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.Notification;
import com.example.ecommerce.domain.entities.Order;
import com.example.ecommerce.repository.NotificationRepository;
import com.example.ecommerce.service.event.Observer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener {
    private final NotificationRepository notificationRepository;
    public  Observer<Order> orderCreateListener() {
        return order -> {

            int totalItem = order.getLineItems().stream()
                    .mapToInt(l -> l.getItems().size())
                    .sum();
            Notification notification = Notification.builder()

                    .message(String.format(
                            "You created successfully order with id: %s.\nTotal item is: %s\nTotal price is: %s",
                            order.getId(),
                            totalItem,
                            order.getTotalPrice()))
                    .build();
            //save notification on website
            notificationRepository.save(notification);
            //send notification to email
            Utils.sendToEmail(notification, "Order Product");
            log.info(SecurityUtils.getUsername() + " created order success");
        };
    }
    public  Observer<Order> orderPaymentListener() {
        return order -> {
            Notification notification = Notification.builder()
                    .message(String.format("You paid successfully order with id: %s.", order.getId()))
                    .build();
            notificationRepository.save(notification);
            Utils.sendToEmail(notification, "Payment Product");
            log.info(SecurityUtils.getUsername() + " paid for the order with id " + order.getId());
        };
    }
}
