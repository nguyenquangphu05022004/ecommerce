package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.test.AssertUtils;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.logger.OperationLoggerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.trade.controller.admin.order.self.vo.PageOrderReqVO;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLog;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderLogRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderRepository;
import com.example.ecommerce.trade.enums.OrderStatus;
import com.example.ecommerce.trade.enums.PaymentStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static com.example.ecommerce.system.enums.SysErrorCodeConstants.NOTIFY_TEMPLATE_NOT_FOUND;
import static com.example.ecommerce.trade.enums.OrderPlace.LIVESTREAM;
import static com.example.ecommerce.trade.enums.OrderPlace.NORMAL;
import static com.example.ecommerce.trade.enums.OrderStatus.*;
import static com.example.ecommerce.trade.enums.OrderStatus.DELIVERED;
import static com.example.ecommerce.trade.enums.PaymentMode.BANK;
import static com.example.ecommerce.trade.enums.PaymentMode.RECEIPT;
import static com.example.ecommerce.util.RandomEntity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OrderServiceTest extends TestBase {

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderService orderService;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private ProductSpuRepository spuRepository;
    @Autowired private ProductSkuRepository skuRepository;
    @Autowired private OperationLoggerRepository operationLoggerRepository;
    @Autowired private OrderLogRepository orderLogRepository;
    @Autowired private JdbcTemplate jdbcTemplate;


    @Test
    void createOrder_success() {
        UserMember member = userMemberRepository.save(userMember(Seller.class));
        ProductSpu spu = spuRepository.save(spu((Seller) member));
        ProductSku sku = skuRepository.save(sku(spu));
        Cart cart = cartRepository.save(cart(sku, member));

        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, r -> {
            r.setCouponIds(null); r.setUserMemberId(member.getId()); r.setCartIds(List.of(cart.getId()));
        });
        Long orderId = orderService.createOrder(req);
        try {
            //wait for notification, write order log
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Order order = orderRepository.findById(orderId).get();
        List<OrderLog> orderLogs = this.orderLogRepository.findAll();
        /**
         * template create order is not exists -> write error in OperationLog
         * else -> NotifyMessage
         */
        OperationLogger operationLogger = this.operationLoggerRepository.findAll().get(0);

        assertNotEquals(order, null);
        assertNotEquals(operationLogger, null);
//        assertEquals(orderLogs.size(), 1);
        AssertUtils.assertPojoEquals(order.getLineItems().get(0).getSeller(), (Seller)member);
        AssertUtils.assertPojoEquals(order.getLineItems().get(0).getItems().get(0).getProductSku(), sku);
        assertEquals(operationLogger.getReturnMessage().contains(NOTIFY_TEMPLATE_NOT_FOUND.getMessage()), true);
    }


    @Test
    void getPageOrder_success() {
        createOrder_success(); createOrder_success(); createOrder_success();
        List<Order> orders = this.orderRepository.findAll();

        Order o1 = orders.get(0);
        o1.setOrderPlace(NORMAL); o1.setPaymentMode(RECEIPT); o1.setCombinationOfSellers(false);
        o1.setOrderStatus(PROCESSING); o1.setPaymentStatus(PaymentStatus.PROCESSING);

        Order o2 = orders.get(1); o2.setOrderPlace(LIVESTREAM);
        o2.setPaymentMode(BANK); o2.setCombinationOfSellers(true); o2.setOrderStatus(DELIVERED);

        Order o3 = orders.get(2);
        o3.setOrderStatus(DELIVERED); o3.setPaymentMode(RECEIPT);

        this.orderRepository.save(o1);
        this.orderRepository.save(o2);
        this.orderRepository.save(o3);

        PageOrderReqVO req = new PageOrderReqVO();
        req.setOrderStatus(DELIVERED);
        req.setPaymentMode(RECEIPT);

        PageResult<Order> pageOrder = orderService.getPageOrder(req);

        assertEquals(pageOrder.getTotalPage(), 1);
        assertEquals(pageOrder.getList().size(), 1);

    }

    @Test
    void updateNextStatus_success() {
        String content = "Don hang cua ban da duoc chap thuan, dang duoc xu ly";
        Order order = new Order(); order.setOrderStatus(PENDING); orderRepository.save(order);
        OrderLog orderLog = new OrderLog(); orderLog.setNextStatus(PROCESSING); orderLog.setOrder(order);
        this.orderLogRepository.save(orderLog);

        orderService.updateNextStatus(order.getId(), content);

        Order updateOrder = orderRepository.findById(order.getId()).get();

        assertEquals(updateOrder.getOrderStatus(), next(order.getOrderStatus()));

    }

}