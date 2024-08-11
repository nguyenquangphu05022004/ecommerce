package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.domain.entities.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterOrderRequest {
    private OrderStatus orderStatus;
    private int page = 1;
    private int limit = 20;
}
