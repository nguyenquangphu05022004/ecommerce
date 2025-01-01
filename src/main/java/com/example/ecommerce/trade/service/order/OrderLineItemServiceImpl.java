package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.service.notify.NotifySendService;
import com.example.ecommerce.trade.controller.admin.order.line.vo.PageOrderLineItemReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import com.example.ecommerce.trade.dal.repo.order.OrderLineItemRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.trade.enums.ErrorConstants.LINE_ITEM_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderLineItemServiceImpl implements OrderLineItemService{
    private final OrderLineItemRepository orderLineItemRepository;
    private final NotifySendService notifySendService;
    private final OrderService orderService;
    @Override
    public OrderLineItem updateItemsGranted(Long orderLineItemId, Boolean granted) {
        OrderLineItem orderLineItem = getOrderLineById(orderLineItemId);
        orderLineItem.setOrderIsGranted(granted);
        this.orderLineItemRepository.save(orderLineItem);

        Map<String, Object> properties = new HashMap<>();
        properties.put("orderId", orderLineItem.getOrder().getId());
        properties.put("orderNo", orderLineItem.getOrder().getNo());
        properties.put("orderLineItemId", orderLineItem.getId());
        properties.put("itemsName", orderLineItem.itemsName());

        /**
         * Send to seller
         */
        notifySendService.notifySingleMessage(
                orderLineItem.getSeller().getId(),
                "update_item_granted",
                properties
        );
        return orderLineItem;
    }


    @Override
    public PageResult<OrderLineItem> getPageOrderLineItem(PageOrderLineItemReqVO req) {
        Specification<OrderLineItem> spec =(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.between(root.get("createdDate"), req.getStart(), req.getEnd());
            if(req.getOrderStatus() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("order").get("orderStatus"), req.getOrderStatus()));
            }
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("seller").get("id"), req.getUserMemberId()));
            return  predicate;
        };

        return new PageResult<>(orderLineItemRepository.findAll(spec, req.buildPageRequest()));
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public OrderLineItem updateItemsAreDeliveredToWarehouse(Long orderLineItemId, Boolean checkSum) {
        OrderLineItem orderLineItem = getOrderLineById(orderLineItemId);
        if(checkSum) {
            int numberOfLineIsNotCompleted = orderLineItemRepository.countByOrderIdAndItemsAreDeliveredToWareHouse(
                    orderLineItem.getOrder().getId(),
                    false
            );
            /**
             * LineItem cuoi cung chua duoc hoan thanh
             */
            if(numberOfLineIsNotCompleted == 1 && !orderLineItem.getItemsAreDeliveredToWareHouse()) {
                orderLineItem.setItemsAreDeliveredToWareHouse(true);
                orderLineItemRepository.save(orderLineItem);
                orderService.updateNextStatus(
                        orderLineItem.getOrder().getId(),
                        "Toan bo san pham ma ban da dat hang da duoc chuyen vao kho va chuan bi van chuyen den dia chi cua ban"
                );
            }
            return orderLineItem;
        } else {
            throw new UnsupportedOperationException("Unsupported when checkSum = false for method: updateItemsAreDeliveredToWarehouse");
        }
    }

    @Override
    public OrderLineItem getOrderLineById(Long id) {
       return this.orderLineItemRepository.findById(id)
               .orElseThrow(() -> exception(LINE_ITEM_NOT_FOUND));
    }
}
