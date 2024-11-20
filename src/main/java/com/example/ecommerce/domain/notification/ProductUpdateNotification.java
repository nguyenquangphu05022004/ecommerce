package com.example.ecommerce.domain.notification;

import com.example.ecommerce.domain.entities.Product;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Vendor;
import com.example.ecommerce.repository.NotificationRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Table(name = "product_update_notifications")
@Entity
@DiscriminatorValue("PRODUCT")
@NoArgsConstructor
public class ProductUpdateNotification extends Notification{
    private Product product;

    public ProductUpdateNotification(User fromUser, Product product) {
        super(fromUser);
        this.product = product;
    }

    @Override
    public void send(NotificationRepository notificationRepository,
                     SimpMessagingTemplate messagingTemplate) {
        Vendor vendor = (Vendor) getFromUser();
        String message = "Vendor: updated new product, maybe product which you like";
        setMessage(message);
        notificationRepository.save(this);
        vendor.getUsersFavorite()
                .forEach(s -> {
                    messagingTemplate.convertAndSend("", message);
                });
    }
}
