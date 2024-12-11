package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.system.controller.user.vo.AddressResVO;
import com.example.ecommerce.system.dal.dataobject.user.Address;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.AddressRepository;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutReqVO;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutResVO;
import com.example.ecommerce.trade.controller.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.dataobject.order.OrderItem;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderStatus;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderItemRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.STOCK_NOT_ENOUGH;
import static com.example.ecommerce.trade.enums.ErrorConstants.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final ProductSkuRepository productSkuRepository;
    private final SellerRepository sellerRepository;
    @Override
    @Transactional
    public void createOrder(Long userId, OrderDetailsReqVO reqVO) {
        Order order = Order.builder().orderStatus(OrderStatus.PENDING)
                .no(System.currentTimeMillis() + "").commentStatus(false)
                .paymentMode(reqVO.getPaymentMode())
                .address(this.addressRepository.findById(reqVO.getAddressId()).get().detailAddress())
                .userMember(UserMember.builder().id(userId).build()).build();
        Set<Cart> carts = convertSet(reqVO.getCartIds(), cartId -> {
            return this.cartRepository.findById(cartId).orElseThrow(() -> exception(CART_NOT_FOUND));
        });

        Set<ProductSku> productSkus = convertSet(carts, cart -> {
            ProductSku sku = cart.getProductSku();
            if (sku.getQuantity() < cart.getQuantity()) {
                throw exception(STOCK_NOT_ENOUGH);
            }
            sku.setQuantity(sku.getQuantity() - cart.getQuantity());
            return sku;
        });

        Set<OrderItem> orderOrderItems = convertSet(carts, cart -> {
            return OrderItem.builder().quantity(cart.getQuantity())
                    .productSku(cart.getProductSku())
                    .order(order)
                    .build();
        });

        this.orderRepository.save(order);
        this.orderItemRepository.saveAll(orderOrderItems);
        this.productSkuRepository.saveAll(productSkus);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> exception(ORDER_NOT_FOUND));
        if(order.getOrderStatus() == OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.CANCEL);
            this.orderRepository.save(order);
        } else {
            throw exception(ORDER_APPROVAL);
        }
    }

    @Override
    public OrderCheckoutResVO checkout(Long userId, OrderCheckoutReqVO reqVO) {
        Address address = this.addressRepository
                .findByUserIdAndDefaultAddress(userId, true)
                .orElse(null);

        Set<Cart> carts = convertSet(reqVO.getCartIds(), cartId -> {
            return this.cartRepository.findById(cartId).orElseThrow(() -> exception(CART_NOT_FOUND));
        });

        Map<Seller, Set<Cart>> sellerMapSetCart = convertToMapSet(convertSet(carts, cart -> {
            return new Pair<>(cart.getProductSku().getProductSpu().getSeller(), cart);
        }));
        Set<OrderCheckoutResVO.LineItem> lineItems = sellerMapSetCart.entrySet().stream()
                .map(s -> new OrderCheckoutResVO.LineItem(s.getKey(), s.getValue()))
                .collect(Collectors.toSet());

        return OrderCheckoutResVO.builder()
                .address(address == null ? null : new AddressResVO(address))
                .lineItems(lineItems).build();
    }

    @Override
    public List<Order> getAllListOrder(Long userId) {
        return this.orderRepository.findAllByUserMemberId(userId);
    }

    @Override
    public List<Order> getAllListOrderByStatus(Long userId, OrderStatus orderStatus) {
        return this.orderRepository.findAllByUserMemberIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public void updateNextStatus(Long orderId) {

    }

    @Override
    public void updatePreviousStatus(Long orderId) {

    }


}
