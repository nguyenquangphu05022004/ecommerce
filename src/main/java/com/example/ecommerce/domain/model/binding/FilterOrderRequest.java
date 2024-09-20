package com.example.ecommerce.domain.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterOrderRequest {
    private String orderStateName;
    private int page = 1;
    private int limit = 30;
}
