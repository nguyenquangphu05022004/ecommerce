package com.example.ecommerce.service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class CartRequest {
    private Long stockId;
    private String operation;
    private Long stockClassificationId;
}
