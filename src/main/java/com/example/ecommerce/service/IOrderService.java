package com.example.ecommerce.service;


import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequest request);
    List<OrderViewModel> getAllOrderByCustomer(OrderStatus status);
    void updatePayment(Long orderId);

    void deleteById(Long orderId);
}
