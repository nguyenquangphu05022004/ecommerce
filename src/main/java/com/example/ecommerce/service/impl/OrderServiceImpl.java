package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.impl.OrderConverter;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IGenericService<OrderDto>, IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderConverter orderConverter;
    private final CouponRepository couponRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            OrderConverter orderConverter,
                            CouponRepository couponRepository,
                            BasketRepository basketRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderConverter = orderConverter;
        this.couponRepository = couponRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public List<OrderDto> records() {
        return  orderRepository.findAllByUserUsername(SecurityUtils.username())
                .stream()
                .map(entity -> orderConverter.toDto(entity))
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
    public OrderDto saveOrUpdate(OrderDto orderDto) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        user.setUserContactDetails(orderDto.getUser().getUserContactDetails());
        Product product = Product.builder()
                .id(orderDto.getProduct().getId())
                .build();
        Order order = orderConverter.toEntity(orderDto);
        if(orderDto.getCouponId() != null) {
            Optional<Coupon> opCoupon = couponRepository.findByIdAndProductIdAndExpiredIsFalse(
                    orderDto.getCouponId(), product.getId()
            );
            if(!opCoupon.isEmpty()) {
                order.setPercent(opCoupon.get().getPercent());
            }
        }
        order.setUser(user);
        order.setProduct(product);
        orderRepository.save(order);
        basketRepository.deleteByProductIdAndUserId(product.getId(), user.getId());
        return null;
    }

    @Override
    public List<OrderDto> records(Status status) {
        return  orderRepository
                .findAllByUserUsernameAndBillStatus(SecurityUtils.username(), status)
                .stream()
                .map(entity -> orderConverter.toDto(entity))
                .collect(Collectors.toList());
    }
}
