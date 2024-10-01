package com.example.ecommerce.service.event.listener;

public enum NotificationActionType {
    ORDER, //order create, order is changed state to user
    PRODUCT, //when user follow vendor who created product and inform to the users
    HAPPY_BIRTHDAY, //happy birthday user
    FAVORITE //inform to vendor when a user favorite
}
