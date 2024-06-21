package com.example.ecommerce.domain.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    private Long stockId;
    private Long stockClassificationId;
    private Character operator;
}
