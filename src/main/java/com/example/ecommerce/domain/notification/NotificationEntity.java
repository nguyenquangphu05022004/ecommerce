package com.example.ecommerce.domain.notification;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.User;
import lombok.Getter;

@Getter
public class NotificationEntity extends BaseEntity {
    private String message;
    private User fromUser;




}
