package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.service.dto.SelectFilterOrder;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final EventRepository eventRepository;
    @Qualifier("orderMapper")
    private final IMapper<Order, OrderRequest, OrderDto> mapper;

    @Override
    @Transactional
    public void createOrder(OrderRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getFullName(), "fullName");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getDistrict(), "district");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getAddress(), "address");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getWard(), "ward");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getPhoneNumber(), "phoneNumber");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getProvince(), "province");
        ValidationUtils.fieldCheckNullOrEmpty(request.getPayment());
        // convert to order entity
        Order order = mapper.toEntity(request);
        //save order of user to database
        Order saved = orderRepository.save(order);
        //notify to user when a order was created by current user;
        saved.notify(notificationRepository);
        //event auto approval order after 8 hours
        eventRepository.createEvent(saved.getId());
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
