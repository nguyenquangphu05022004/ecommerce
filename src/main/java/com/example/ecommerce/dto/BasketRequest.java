package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    private Integer quantity;
    private Integer totalPrice;
    private Long productTypeId;
    private Character operator;
}
