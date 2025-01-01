package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.dal.repo.coupon.CouponRepository;
import com.example.ecommerce.promotion.service.coupon.CouponService;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.notify.NotifySendService;
import com.example.ecommerce.trade.controller.admin.order.self.vo.PageOrderReqVO;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderItem;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLog;
import com.example.ecommerce.trade.enums.OrderStatus;
import com.example.ecommerce.trade.dal.repo.order.OrderItemRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderLineItemRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderRepository;
import com.example.ecommerce.trade.enums.PaymentStatus;
import com.example.ecommerce.trade.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMap;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.trade.enums.ErrorConstants.ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final NotifySendService notifySendService;
    private final CartService cartService;
    private final OrderLogService orderLogService;
    private final CouponRepository couponRepository;
    private final CouponService couponService;
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void createOrder(OrderDetailsReqVO reqVO) {
        Order order = Order.builder().orderStatus(OrderStatus.PENDING)
                .no(System.currentTimeMillis() + "").paymentMode(reqVO.getPaymentMode())
                .addressDetails(reqVO.getAddressDetails())
                .paymentStatus(PaymentStatus.PROCESSING)
                .userMember(UserMember.builder().id(reqVO.getUserId()).build())
                .build();
        /**
         * Map seller wth coupon
         */
        Map<UserMember, Coupon> couponMap = convertToMap(convertList(
                convertList(reqVO.getCouponIds(), id -> couponService.getCouponById(id)), coupon -> {
                    coupon.decrement();
                    return new Pair<>(coupon.getOwner(), coupon);
                }
        ));

        /**
         * Map seller with list (product, quantity)
         */
        Map<Seller, Set<Pair<ProductSku, Integer>>> productMap = convertToMapSet(convertSet(
                convertList(reqVO.getCartIds(), id -> cartService.getCartById(id)), cart -> {
                    return new Pair<>(
                        cart.getProductSku().getProductSpu().getSeller(),
                        new Pair<>(cart.getProductSku(), cart.getQuantity())
                );
        }));

        List<OrderLineItem> lineItems = convertList(productMap.entrySet(), entry -> {
            OrderLineItem orderLineItem = OrderLineItem.builder().order(order)
                    .commentStatus(false).seller(entry.getKey())
                    .coupon(couponMap.get(entry.getKey()))
                    .build();

            List<OrderItem> orderItems = convertList(entry.getValue(), pairSkuQuan -> {
                return OrderItem.builder().productSku(pairSkuQuan.getKey())
                        .orderLineItem(orderLineItem).quantity(pairSkuQuan.getValue())
                        .build();
            });
            orderLineItem.setItems(orderItems);

            return orderLineItem;
        });

        if(CollUtils.size(lineItems) > 1) {
            order.setCombinationOfSellers(true);
        }
        cartService.deleteAll(reqVO.getCartIds());
        this.orderRepository.save(order);
        this.orderLogService.createOrderLog(order.getId(),
                "Ban da dat hang vao luc: " + DateTimeUtils.format(LocalDateTime.now()),
                null, OrderStatus.PENDING);
        this.orderLineItemRepository.saveAll(lineItems);
        convertList(lineItems, l -> this.orderItemRepository.saveAll(l.getItems()));
        convertList(couponMap.entrySet(), entry -> this.couponRepository.save(entry.getValue()));

        notifySendService.notifySingleMessage(reqVO.getUserId(), "create_order", buildProperties(order));
    }

    @Override
    public void cancelOrder(Long userId, Long orderId) {
        Order order =getOrderByUserIdAndOrderId(userId, orderId);
        order.setOrderStatus(OrderStatus.CANCEL);
        convertList(convertList(order.getLineItems(), OrderLineItem::getCoupon), coupon -> {
            coupon.decrement();
            this.couponRepository.save(coupon);
            return null;
        });
        this.orderRepository.save(order);
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
    public void updateNextStatus(Long orderId, String content) {
        Order currentOrder = getOrderById(orderId);
        OrderLog currentOrderLog = orderLogService.getLatestLogByOrderId(orderId);

        OrderStatus currentStatus = currentOrder.getOrderStatus();

        currentOrder.setOrderStatus(currentOrderLog.getNextStatus());

        this.orderRepository.save(currentOrder);
        this.orderLogService.createOrderLog(
                orderId, content,
                currentStatus, OrderStatus.next(currentOrder.getOrderStatus())
        );

        notifySendService.notifySingleMessage(orderId, "update_order_status", buildProperties(currentOrder));

    }

    @Override
    public void updatePreviousStatus(Long orderId, String content) {
        Order currentOrder = getOrderById(orderId);
        OrderLog currentOrderLog = orderLogService.getLatestLogByOrderId(orderId);

        OrderStatus currentStatus = currentOrder.getOrderStatus();

        currentOrder.setOrderStatus(currentOrderLog.getPreviousStatus());

        this.orderRepository.save(currentOrder);
        this.orderLogService.createOrderLog(
                orderId, content,
                OrderStatus.prev(currentOrder.getOrderStatus()), currentStatus
        );
    }

    @Override
    public void approvalOrder(Long orderId) {
        Order order = getOrderById(orderId);
        if(order.getOrderStatus() == OrderStatus.PENDING) {
            updateNextStatus(orderId, "Order da duoc chap thuan");
            /**
             * Send notification over email, app to user
             */
        }
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> exception(ORDER_NOT_FOUND));
    }

    @Override
    public Order getOrderByUserIdAndOrderId(Long userId, Long orderId) {
        return orderRepository.findByUserMemberIdAndId(userId, orderId)
                .orElseThrow(() -> exception(ORDER_NOT_FOUND));
    }

    @Override
    public boolean userHasOrderProduct(Long userId, Long spuId) {
        return false;
    }

    @Override
    public PageResult<Order> getPageOrder(PageOrderReqVO req) {
        return null;
    }


    private Map<String, Object> buildProperties(Order order) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("orderId", order.getId());
        properties.put("orderNo", order.getNo());
        properties.put("totalProduct", order.totalProduct());
        properties.put("totalPrice", order.totalPrice());
        properties.put("createdDate", DateTimeUtils.format(order.getCreatedDate()));
        properties.put("status", order.getOrderStatus().getValue());
        return properties;
    }

}
