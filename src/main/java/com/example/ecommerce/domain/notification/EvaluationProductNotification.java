package com.example.ecommerce.domain.notification;

import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.Product;
import com.example.ecommerce.repository.NotificationRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Entity
@Table(name = "eval_product_notification")
@DiscriminatorValue("EVALUATION")
public class EvaluationProductNotification extends Notification{
    private Product product;
    private Evaluation evalReply;

    @Override
    public void send(NotificationRepository notificationRepository,
                     SimpMessagingTemplate messagingTemplate) {

    }
}
