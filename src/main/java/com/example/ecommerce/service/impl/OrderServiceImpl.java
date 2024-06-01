package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.impl.OrderConverter;
import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IGenericService<OrderDto>, IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderConverter orderConverter;
    private final CouponRepository couponRepository;
    private final BasketRepository basketRepository;
    private final StockRepository stockRepository;


    @Override
    public List<OrderDto> records() {
        return orderRepository.findAllByUserUsername(SecurityUtils.username())
                .stream()
                .map(entity -> OrderDto.builder()
                        .id(entity.getId())
                        .approval(entity.isApproval())
                        .payment(entity.getPayment())
                        .quantity(entity.getQuantity())
                        .couponPercent(entity.getCouponPercent())
                        .stockResponse(StockServiceImpl
                                .getStockResponse(entity.getStock()))
                        .build())
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
        return orderConverter.toDto(orderRepository.findById(id).get());
    }

    @Override
    @Transactional
    public OrderDto saveOrUpdate(OrderRequest orderDto) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        user.setUserContactDetails(orderDto.getContactDetails());
        Stock stock = stockRepository.findById(orderDto.getStockId())
                .orElseThrow(() -> new NotFoundException("StockId", orderDto.getStockId() + ""));
        Order order = Order.builder()
                .stock(stock)
                .payment(orderDto.getPayment())
                .quantity(orderDto.getQuantity())
                .user(user)
                .build();
        orderRepository.save(order);
        basketRepository.deleteByStockIdAndUserId(stock.getId(), user.getId());
        return null;
    }

    @Override
    public List<OrderDto> records(Status status) {
        return orderRepository
                .findAllByUserUsernameAndBillStatus(SecurityUtils.username(), status)
                .stream()
                .map(entity -> orderConverter.toDto(entity))
                .collect(Collectors.toList());
    }
}
