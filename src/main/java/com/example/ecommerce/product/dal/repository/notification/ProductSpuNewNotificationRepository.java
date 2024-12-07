package com.example.ecommerce.product.dal.repository.notification;

import com.example.ecommerce.product.dal.dataobject.notification.ProductSpuNewNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpuNewNotificationRepository extends JpaRepository<ProductSpuNewNotification, Long> {
}
