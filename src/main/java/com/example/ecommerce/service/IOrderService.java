package com.example.ecommerce.service;

import com.example.ecommerce.domain.Status;
import com.example.ecommerce.domain.dto.OrderDto;
import com.example.ecommerce.domain.dto.OrderRequest;
import com.example.ecommerce.domain.dto.SelectFilterOrder;

import java.util.List;

public interface IOrderService extends IGenericService<OrderDto>{
    OrderDto saveOrUpdate(OrderRequest orderRequest);
    List<OrderDto> getAllOrderOfCustomer(Status status);
    void approval(Long orderId);
    void updatePayment(Long orderId);
    List<OrderDto> getAllOrderOfVendor(SelectFilterOrder status);

}
