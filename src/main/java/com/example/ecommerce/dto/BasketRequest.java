package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    private Long stockId;
    private Character operator;
}
