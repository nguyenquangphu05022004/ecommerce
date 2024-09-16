package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.domain.entities.order.Order;


public interface OrderState {
   String next(Order order);
   String prev(Order order);
   String status();
}
