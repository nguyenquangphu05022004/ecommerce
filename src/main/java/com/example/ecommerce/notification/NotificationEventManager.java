package com.example.ecommerce.notification;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NotificationEventManager {
    private final List<NotificationObserver> notificationObservers;

    public NotificationEventManager() {
        this.notificationObservers = new ArrayList<>();
    }

    public void addNotificationEvent(NotificationObserver no) {
        if(!this.notificationObservers.contains(no)) {
            this.notificationObservers.add(no);
        }
    }

    public void notify(NotificationEvent event, String onObjectId) {
        for(NotificationObserver ob : this.notificationObservers) {
            executeEvent(ob, event, onObjectId);
        }
    }

    private void executeEvent(NotificationObserver ob, NotificationEvent event, String onObjectId) {
        Method[] declaredMethods = ob.getClass().getInterfaces()[0].getDeclaredMethods();
        for(Method method : declaredMethods) {
            EventType annotation = method.getAnnotation(EventType.class);
            if(annotation != null && annotation.eventType() == event) {
                try {
                    method.invoke(ob, onObjectId);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
