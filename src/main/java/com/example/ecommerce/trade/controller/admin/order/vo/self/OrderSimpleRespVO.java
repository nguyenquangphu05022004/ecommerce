package com.example.ecommerce.trade.controller.admin.order.vo.self;

import com.example.ecommerce.system.controller.app.user.vo.UserMemberResVO;
import com.example.ecommerce.trade.controller.app.order.vo.AppOrderSimpleRespVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSimpleRespVO extends AppOrderSimpleRespVO {
    private UserMemberResVO userMemberResVO;
    public OrderSimpleRespVO(Order order) {
        super(order);
        this.userMemberResVO = new UserMemberResVO(order.getUserMember());
    }
}
