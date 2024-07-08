package com.example.ecommerce.service.request;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BasketRequest {
    private Long stockId;
    private Long stockClassificationId;
    private String operator;
}
