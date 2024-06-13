package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.domain.dto.product.OrderDto;
import com.example.ecommerce.domain.dto.product.OrderRequest;
import com.example.ecommerce.domain.dto.ENUM.SelectFilterOrder;

import java.util.List;

public interface IOrderService {
    OrderDto saveOrUpdate(OrderRequest orderRequest);
    List<OrderDto> records(Status status);
    void approval(Long orderId);
    void updatePayment(Long orderId);

    /**
     *
     * Method get list order that are created by user of vendor
     */
    List<OrderDto> getAllOrder();
    List<OrderDto> getAllOrder(SelectFilterOrder status);

}
