package com.example.ecommerce.service;


import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.domain.dto.SelectFilterOrder;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.request.OrderRequest;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequest request);
    List<OrderDto> getAllOrderByCustomer(OrderStatus status);
    List<OrderDto> getAllOrderOfVendor(SelectFilterOrder selectFilerOrder);
    void approval(Long orderId, Boolean approval);
    void updatePayment(Long orderId);

    void deleteById(Long orderId);
}
