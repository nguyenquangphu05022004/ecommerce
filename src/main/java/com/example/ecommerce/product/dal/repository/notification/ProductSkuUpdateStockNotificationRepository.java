package com.example.ecommerce.product.dal.repository.notification;

import com.example.ecommerce.product.dal.dataobject.notification.ProductSkuUpdateStockNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductSkuUpdateStockNotificationRepository extends JpaRepository<ProductSkuUpdateStockNotification, Long> {
}
