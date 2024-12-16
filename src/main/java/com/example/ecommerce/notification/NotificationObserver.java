package com.example.ecommerce.notification;


import static com.example.ecommerce.notification.NotificationEvent.*;

public interface NotificationObserver {
    @EventType(eventType = ON_STOCK)
    void onStockUpdate(String productSkuIdStr);
    @EventType(eventType = ON_ORDER_CREATE)
    void onCreatedNewOrder();
    @EventType(eventType = ON_NEW_PRODUCT)
    void onCreateNewProduct(String productSpuIdStr);
    @EventType(eventType = ON_ORDER_DELIVERY)
    void onOrderDelivery();
    @EventType(eventType = ON_STOCK)
    default void onForgetPassword(){};
}
