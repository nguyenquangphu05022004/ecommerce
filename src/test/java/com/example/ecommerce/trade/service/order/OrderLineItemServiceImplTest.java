package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.repository.logger.OperationLoggerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import com.example.ecommerce.trade.dal.repo.cart.CartRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderLineItemRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderLogRepository;
import com.example.ecommerce.trade.dal.repo.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderLineItemServiceImplTest extends TestBase {

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderService orderService;
    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private ProductSpuRepository spuRepository;
    @Autowired private ProductSkuRepository skuRepository;
    @Autowired private OperationLoggerRepository operationLoggerRepository;
    @Autowired private OrderLogRepository orderLogRepository;
    @Autowired private OrderLineItemService orderLineItemService;

    @Test
    void updateItemsGranted_when_template_granted_not_found_success() {
        Order order = new Order();
        Seller seller = new Seller();
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setOrderIsGranted(false);
        orderLineItem.setOrder(order);
        orderLineItem.setSeller(seller);
        orderRepository.save(order);
        userMemberRepository.save(seller);
        orderLineItemRepository.save(orderLineItem);

        orderLineItemService.updateItemsGranted(orderLineItem.getId(), true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        orderLineItem = this.orderLineItemRepository.findById(orderLineItem.getId()).get();

        //Not found template name -> record error was stored in table operation_log
        OperationLogger operationLogger = this.operationLoggerRepository.findAll().get(0);

        assertEquals(orderLineItem.getOrderIsGranted(), true);
//        assertEquals(operationLogger.getReturnCode(), NOTIFY_TEMPLATE_NOT_FOUND.getCode());
//        assertEquals(operationLogger.getReturnMessage().contains("Not found template name"), true);
    }

    @Test
    void getPageOrderLineItem() {
    }

    @Test
    void updateItemsAreDeliveredToWarehouse() {
    }

    @Test
    void getOrderLineById() {
    }
}