package com.example.ecommerce.domain;

public interface Observer<Notification>{
    void notification(Notification notification);
}