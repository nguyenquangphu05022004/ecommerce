package com.example.ecommerce.service.impl;

import com.example.ecommerce.constant.Convert;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IGenericService<OrderDto>, IOrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> records() {
//        String username = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
        return  orderRepository.findAll()
                .stream()
                .map(entity -> (OrderDto)Convert.ORDER.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return orderRepository.count();
    }

    @Override
    public OrderDto findById(Long id) {
        return (OrderDto) Convert.ORDER.toDto(
                GenericService.findById(orderRepository, id)
        );
    }

    @Override
    public OrderDto saveOrUpdate(OrderDto orderDto) {
        Order order = (Order) Convert.ORDER.toEntity(orderDto);
        return (OrderDto) Convert.ORDER.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> records(Status status) {
        //        String username = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
        return  orderRepository.findAllByBillStatus(status)
                .stream()
                .map(entity -> (OrderDto)Convert.ORDER.toDto(entity))
                .collect(Collectors.toList());
//        return null;
    }
}
