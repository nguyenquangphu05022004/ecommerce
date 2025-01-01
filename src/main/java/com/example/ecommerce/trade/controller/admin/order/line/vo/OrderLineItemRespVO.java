package com.example.ecommerce.trade.controller.admin.order.line.vo;

import com.example.ecommerce.trade.controller.app.order.vo.AppOrderDetailsRespVO;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import lombok.Getter;

@Getter
public class OrderLineItemRespVO extends AppOrderDetailsRespVO.OrderLineItemRespVO {
    private Long orderId;
    private String orderNo;

    public OrderLineItemRespVO(OrderLineItem orderLineItem) {
        super(orderLineItem);
        this.orderId = orderLineItem.getOrder().getId();
        this.orderNo = orderLineItem.getOrder().getNo();
    }
}
