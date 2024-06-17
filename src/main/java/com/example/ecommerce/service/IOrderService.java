package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.domain.dto.product.OrderDto;
import com.example.ecommerce.domain.dto.product.OrderRequest;
import com.example.ecommerce.domain.dto.ENUM.SelectFilterOrder;

import java.util.List;

public interface IOrderService extends IGenericService<OrderDto>{
    OrderDto saveOrUpdate(OrderRequest orderRequest);
    List<OrderDto> getAllOrderOfCustomer(Status status);
    void approval(Long orderId);
    void updatePayment(Long orderId);

    List<OrderDto> getAllOrderOfVendor(SelectFilterOrder status);

}
