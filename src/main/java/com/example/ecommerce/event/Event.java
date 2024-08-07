package com.example.ecommerce.event;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Event {

    private static Event event = null;
    private  Map<EventType, Observer> subscribes;
    private Event() {
        subscribes = new HashMap<>();
    }
    public static Event getInstance() {
        if(event == null) {
            event = new Event();
        }
        return event;
    }

    public void postEvent(EventType eventType, final Object object) {
        if(event == null) return;
        if(subscribes.containsKey(eventType)) {
            subscribes.get(eventType).notify(object);
        }
    }
    public void subscribe(EventType eventType, Observer observer) {
        if(!subscribes.containsKey(eventType)) {
            subscribes.put(eventType, observer);
            log.info("{} subscribe", eventType.name());
        }
    }

    public enum EventType {
        ORDER_CREATE, ORDER_DELETE, ORDER_PAYMENT,
        USER_CREATE, USER_DELETE,
        VENDOR_CREATE, VENDOR_DELETE,
        PRODUCT_CREATE, PRODUCT_INVENTORY_UPDATE_QUANTITY,
        USER_FAVORITE_VENDOR, USER_FAVORITE_PRODUCT
    }
}
