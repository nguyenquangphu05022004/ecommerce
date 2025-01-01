package com.example.ecommerce.trade.dal.repo.order;

import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long>, JpaSpecificationExecutor<OrderLineItem> {
    int countByOrderIdAndItemsAreDeliveredToWareHouse(Long orderId, Boolean itemsAreDeliveredToWareHouse);
}
