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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

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
    public Long createOrder(OrderDetailsReqVO reqVO) {
        Order order = Order.builder().orderStatus(OrderStatus.PENDING)
                .no(System.currentTimeMillis() + "").paymentMode(reqVO.getPaymentMode())
                .addressDetails(reqVO.getAddressDetails())
                .paymentStatus(PaymentStatus.PROCESSING)
                .combinationOfSellers(false)
                .orderPlace(reqVO.getOrderPlace())
                .userMember(UserMember.builder().id(reqVO.getUserMemberId()).build())
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
        this.orderLineItemRepository.saveAll(lineItems);
        convertList(lineItems, l -> this.orderItemRepository.saveAll(l.getItems()));
        convertList(couponMap.entrySet(), entry -> this.couponRepository.save(entry.getValue()));

        this.orderLogService.createOrderLog(order.getId(),
                "Đơn hàng đã được đặt thành công, bạn sẽ được xử lý sớm. Sau 8h kể từ khi bạn đặt hàng thì bạn có thể hủy nó đi, sau khi đơn được xử lý thì không thể hủy được nữa.",
                null, OrderStatus.PROCESSING);
        notifySendService.notifySingleMessage(reqVO.getUserMemberId(), "create_order", buildProperties(order));
        return order.getId();
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
    public void updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        Order order = getOrderById(orderId);
        order.setPaymentStatus(paymentStatus);
        orderRepository.save(order);
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
    public PageResult<Order> getPageOrder(PageOrderReqVO req) {
        Specification<Order> spec = (root, query, builder) -> {
            Predicate predicate = null;
            if(req.getStart() != null && req.getEnd() != null) {
                predicate = updatePredicate(
                        builder.between(root.get("createdDate"), req.getStart(), req.getEnd()),
                        builder, predicate, "and");
            }
            if(req.getCombinationOfSellers() != null) {
                predicate = updatePredicate(
                        builder.equal(root.get("combinationOfSellers"), req.getCombinationOfSellers()),
                        builder, predicate, "and");
            }
            if(req.getOrderStatus() != null) {
                predicate = updatePredicate(
                        builder.equal(root.get("orderStatus"), req.getOrderStatus()),
                        builder, predicate, "and");
            }
            if(req.getPaymentMode() != null) {
                predicate = updatePredicate(
                        builder.equal(root.get("paymentMode"), req.getPaymentMode()),
                        builder, predicate, "and");
            }
            if(req.getOrderPlace() != null) {
                predicate = updatePredicate(
                        builder.equal(root.get("orderPlace"), req.getOrderPlace()),
                        builder, predicate, "and");
            }
            if(req.getPaymentStatus() != null) {
                predicate = updatePredicate(
                        builder.equal(root.get("paymentStatus"), req.getPaymentStatus()),
                        builder, predicate, "and");
            }
            return predicate;
        };
        Page<Order> pageOrder = this.orderRepository.findAll(spec, req.buildPageRequest());
        return new PageResult<>(pageOrder);
    }

    private Predicate updatePredicate(Predicate newPredicate,
                                      CriteriaBuilder builder,
                                      Predicate oldPredicate,
                                      String type) {
        System.out.println("--------------------------------------------");
        System.out.println("------------------------------------------");
        if(oldPredicate == null) {
            return newPredicate;
        }
        if(type.equals("and")) {
            return builder.and(oldPredicate, newPredicate);
        }
        return builder.or(oldPredicate, newPredicate);
    }


    private Map<String, Object> buildProperties(Order order) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("orderId", order.getId());
        properties.put("timeLine", DateTimeUtils.format(order.getCreatedDate()));
        properties.put("productNames", "test");
        return properties;
    }

}
