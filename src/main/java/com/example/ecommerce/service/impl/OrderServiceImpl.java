package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.domain.dto.SelectFilterOrder;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    @Qualifier("orderMapper")
    private final IMapper<Order, OrderRequest, OrderDto> mapper;


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
        return mapper.toDtoList(orders);
    }

    @Override
    public void createOrder(OrderRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getFullName(), "fullName");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getDistrict(), "district");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getAddress(), "address");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getWard(), "ward");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getPhoneNumber(), "phoneNumber");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getProvince(), "province");
        ValidationUtils.fieldCheckNullOrEmpty(request.getPayment());
        Order order = mapper.toEntity(request);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAllOrderByCustomer(OrderStatus status) {
        List<Order> listOrder = orderRepository
                .findAllByCreatedByAndOrderStatus(SecurityUtils.username(), status);
        return mapper.toDtoList(listOrder);
    }

    @Override
    public void approval(Long orderId, Boolean approval) {
        orderRepository.updateApprovalById(orderId, approval);
    }

    @Override
    public void updatePayment(Long orderId) {

    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
