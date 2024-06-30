package com.example.ecommerce.domain.dto;

import lombok.Data;

@Data
public class NotificationResponse extends BaseDto{
    private String message;
    private ProductDto product;
}
