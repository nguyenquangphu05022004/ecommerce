package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.trade.controller.admin.order.line.vo.PageOrderLineItemReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import org.springframework.data.domain.Page;

public interface OrderLineItemService {

    /**
     * He thong se cap phep toi seller, de seller co the xu ly items cua minh
     * @param orderLineItemId
     * @param granted
     * @return
     */
    OrderLineItem updateItemsGranted(Long orderLineItemId, Boolean granted);

    /**
     * Get toan bo items cua theo seller, trong 1 order cu the
     * @param req
     * @return
     */
    PageResult<OrderLineItem> getPageOrderLineItem(PageOrderLineItemReqVO req);

    /**
     * Khi tat ca items den kho thi se chuyen sang order sang trang thai ship va thong bao toi user
     * @param orderLineItemId
     * @param checkSum: true or false
     * @return
     */
    OrderLineItem updateItemsAreDeliveredToWarehouse(Long orderLineItemId, Boolean checkSum);

    /**
     * get order line
     * @param id
     * @return
     */
    OrderLineItem getOrderLineById(Long id);
}
