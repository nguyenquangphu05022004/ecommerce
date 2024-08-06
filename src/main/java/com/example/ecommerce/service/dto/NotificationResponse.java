package com.example.ecommerce.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationResponse extends BaseDto{
    private String message;
    private ProductDto product;
}
