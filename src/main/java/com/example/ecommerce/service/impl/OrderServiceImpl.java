package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.Status;
import com.example.ecommerce.domain.dto.OrderDto;
import com.example.ecommerce.domain.dto.OrderRequest;
import com.example.ecommerce.domain.dto.SelectFilterOrder;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }


    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    @Override
    public OrderDto findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public OrderDto saveOrUpdate(OrderRequest orderDto) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrderOfCustomer(Status status) {
        if (status == Status.ALL) {
            return getAll();
        }
        return null;
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
        return null;
    }

}
