package com.example.ecommerce.service.event.listener;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Notification;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.service.event.Observer;
import com.example.ecommerce.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import static com.example.ecommerce.domain.entities.EntityType.Type.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductListener {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    //when product is created then system send notification to
    // user that followed vendor who created product
    public  Observer<Product> createProductListener() {
        return product -> {
            Notification notification = Notification.builder()
                    .notificationActionType(NotificationActionType.VENDOR_UPDATE_PRODUCT)
                    .entityType(EntityType.builder().entityType(PRODUCT).entityId(product.getId()).build())
                    .message(String.format("Vendor: %s created product with name: %s",product.getVendor().getShopName(), product.getLanguage().getNameVn()))
                    .build();
            notificationRepository.save(notification);
            sendMailToFollower(product, notification, "New Product is created maybe you prefer");
            log.info(String.format("Vendor: %s created product with name: %s",product.getVendor().getShopName(), product.getLanguage().getNameVn()));
        };
    }
    public  Observer<ProductInventory> updateQuantityProduct() {
        return inventory -> {
            Notification notification = Notification.builder()
                    .notificationActionType(NotificationActionType.VENDOR_UPDATE_PRODUCT_INVENTORY)
                    .entityType(EntityType.builder().entityType(PRODUCT_INVENTORY).entityId(inventory.getId()).build())
                    .message(String.format("Vendor: %s updated product with name: %s",inventory.getProduct().getVendor().getShopName(), inventory.getProduct().getLanguage().getNameVn()))
                    .build();
            notificationRepository.save(notification);
            sendMailToFollower(inventory.getProduct(), notification, "Vendor updated Product is created maybe you prefer");
        };
    }

    private void sendMailToFollower(Product product, Notification notification, String subject) {
        if (!CollectionUtils.isEmpty(product.getVendor().getUsersFavorite())) {
            product.getVendor().getUsersFavorite().stream().forEach(user -> {
                simpMessagingTemplate.convertAndSendToUser(user.getId().toString(), "/user/topic/private-message", notification);
                Utils.sendToEmail(user.getUsername(), notification, subject);
            });
        }
    }
}
