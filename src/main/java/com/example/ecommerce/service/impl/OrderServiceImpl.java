package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.*;
import com.example.ecommerce.domain.Status;
import com.example.ecommerce.domain.dto.OrderDto;
import com.example.ecommerce.domain.dto.OrderRequest;
import com.example.ecommerce.domain.dto.SelectFilterOrder;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final BasketRepository basketRepository;
    private final StockRepository stockRepository;
    private final StockClassificationRepository stockClassificationRepository;
    private final ModelMapper mapper;


    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAllByUserUsername(SecurityUtils.username())
                .stream()
                .map(entity -> mapToDto(entity))
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    @Override
    public OrderDto findById(Long id) {
        return mapToDto(orderRepository.findById(id).get());
    }

    @Override
    @Transactional
    public OrderDto saveOrUpdate(OrderRequest orderDto) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        user.setUserContactDetails(orderDto.getUserContactDetails());
        Stock stock = stockRepository.findById(orderDto.getStockId())
                .orElseThrow(() -> new NotFoundException("StockId", orderDto.getStockId() + ""));
        Coupon coupon = orderDto.getCouponId() != null ?
                couponRepository
                        .findById(orderDto.getCouponId())
                        .orElseThrow()
                : null;
        StockClassification stockClassification = stockClassificationRepository
                .findById(orderDto.getStockClassificationId())
                .orElseThrow(() -> new NotFoundException(
                        "StockClassificationId",
                        orderDto.getStockClassificationId() + "")
                );
        Order order = Order.builder()
                .stock(stock)
                .payment(orderDto.getPayment())
                .quantity(orderDto.getQuantity())
                .user(user)
                .coupon(coupon)
                .stockClassification(stockClassification)
                .status(Status.NOT_APPROVAL)
                .build();
        orderRepository.save(order);
        basketRepository.deleteByStockIdAndUserId(stock.getId(), user.getId());
        return null;
    }

    @Override
    public List<OrderDto> getAllOrderOfCustomer(Status status) {
        if (status == Status.ALL) {
            return getAll();
        }
        return orderRepository
                .findAllByUserUsernameAndStatus(
                        SecurityUtils.username(),
                        status
                )
                .stream()
                .map(entity -> mapToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void approval(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setApproval(true);
        order.setStatus(Status.PROCESSING);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updatePayment(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("OrderId", orderId + ""));
        order.setPurchased(true);
        StockClassification stockClassification = order.getStockClassification();
        stockClassification.setSeller(order.getQuantity() + stockClassification.getSeller());
        order.setStockClassification(stockClassification);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAllOrderOfVendor(SelectFilterOrder selectFilerOrder) {
        List<Order> orders = new ArrayList<>();
        switch (selectFilerOrder) {
            case ALL:
                orders = orderRepository
                        .findAllByStockProductVendorUserUsername(
                                SecurityUtils.username()
                        );
                break;
            case APPROVAL:
                orders = orderRepository
                        .findAllByStockProductVendorUserUsernameAndApproval(
                                SecurityUtils.username(),
                                true);
                break;
            case NOT_APPROVAL:
                orders = orderRepository
                        .findAllByStockProductVendorUserUsernameAndApproval(
                                SecurityUtils.username(),
                                false);
                break;
            case PURCHASED:
                orders = orderRepository
                        .findAllByStockProductVendorUserUsernameAndPurchased(
                                SecurityUtils.username(),
                                true);
                break;
            case NOT_PURCHASED:
                orders = orderRepository.findAllByStockProductVendorUserUsernameAndPurchased(
                        SecurityUtils.username(),
                        false);
        }
        return orders
                .stream()
                .map(entity -> mapToDto(entity))
                .collect(Collectors.toList());
    }

    private OrderDto mapToDto(Order entity) {
        entity.getUser().setVendor(null);
        entity.getUser().setEvaluations(null);
        entity.getStock().setOrders(null);
        entity.getStock().getProduct().setStocks(null);
        return mapper.map(entity, OrderDto.class);
    }
}
