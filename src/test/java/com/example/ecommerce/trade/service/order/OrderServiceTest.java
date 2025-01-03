package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.AssertUtils;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.logger.OperationLoggerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderLogRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderRepository;
import com.example.ecommerce.util.RandomEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.example.ecommerce.system.enums.SysErrorCodeConstants.NOTIFY_TEMPLATE_NOT_FOUND;
import static com.example.ecommerce.util.RandomEntity.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest extends TestBase {

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderService orderService;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private ProductSpuRepository spuRepository;
    @Autowired private ProductSkuRepository skuRepository;
    @Autowired private OperationLoggerRepository operationLoggerRepository;
    @Test
    void createOrder() {
        UserMember member = userMemberRepository.save(userMember(Seller.class));
        ProductSpu spu = spuRepository.save(spu((Seller) member));
        ProductSku sku = skuRepository.save(sku(spu));
        Cart cart = cartRepository.save(cart(sku, member));

        OrderDetailsReqVO req = RandomUtils.randomPojo(OrderDetailsReqVO.class, r -> {
            r.setCouponIds(null); r.setUserId(member.getId()); r.setCartIds(Set.of(cart.getId()));
        });
        Long orderId = orderService.createOrder(req);
        try {
            //wait for notification, write order log
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Order order = orderRepository.findById(orderId).get();
        /**
         * template create order is not exists -> write error in OperationLog
         * else -> NotifyMessage
         */

        OperationLogger operationLogger = this.operationLoggerRepository.findAll().get(0);

        assertNotEquals(order, null);
        AssertUtils.assertPojoEquals(order.getLineItems().get(0).getSeller(), (Seller)member);
        AssertUtils.assertPojoEquals(order.getLineItems().get(0).getItems().get(0).getProductSku(), sku);
        assertNotEquals(operationLogger, null);
        assertEquals(operationLogger.getReturnMessage().contains(NOTIFY_TEMPLATE_NOT_FOUND.getMessage()), true);
    }

    @Test
    void userHasOrderProduct() {

    }

    @Test
    void getPageOrder() {
    }

    @Test
    void updateNextStatus() {
    }

    @Test
    void updatePreviousStatus() {
    }

    @Test
    void approvalOrder() {
    }
}