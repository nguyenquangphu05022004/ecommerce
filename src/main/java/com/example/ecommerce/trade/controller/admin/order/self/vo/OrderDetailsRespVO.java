package com.example.ecommerce.trade.controller.admin.order.self.vo;

import com.example.ecommerce.trade.controller.app.order.vo.AppOrderDetailsRespVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class OrderDetailsRespVO extends AppOrderDetailsRespVO {

    public OrderDetailsRespVO(Order order) {
        super(order);
    }
}
