package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IGenericService<OrderDto>, IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderDto> records() {
        return  orderRepository.findAllByUserUsername(SecurityUtils.username())
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
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        user.setUserContactDetails(orderDto.getUser().getUserContactDetails());
        Product product = Product.builder()
                .id(orderDto.getProduct().getId())
                .build();
        Order order = (Order) Convert.ORDER.toEntity(orderDto);
        order.setUser(user);
        order.setProduct(product);
        orderRepository.save(order);
        return null;
    }

    @Override
    public List<OrderDto> records(Status status) {
        return  orderRepository
                .findAllByUserUsernameAndBillStatus(SecurityUtils.username(), status)
                .stream()
                .map(entity -> (OrderDto)Convert.ORDER.toDto(entity))
                .collect(Collectors.toList());
    }
}
