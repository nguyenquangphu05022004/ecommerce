package com.example.ecommerce.trade.controller.admin.order.log;


import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLog;
import lombok.Data;

@Data
public class OrderLogRespVO {
    private Long id;
    private String content;
    private String prevOrderStatus;
    private String nextOrderStatus;
    private String createdDate;
    public OrderLogRespVO(OrderLog orderLog) {
        this.id = orderLog.getId();
        this.createdDate = DateTimeUtils.format(orderLog.getCreatedDate());
        this.content = orderLog.getContent();
        this.prevOrderStatus = orderLog.getPreviousStatus() == null ? "" : orderLog.getPreviousStatus().getValue();
        this.nextOrderStatus = orderLog.getNextStatus() == null ? "" : orderLog.getNextStatus().getValue();
    }
}
