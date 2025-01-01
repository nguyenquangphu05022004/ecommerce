package com.example.ecommerce.trade.controller.admin.order.line.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageOrderLineItemReqVO extends PageParam {
    private LocalDateTime start = LocalDateTime.now().minusDays(7);
    private LocalDateTime end = LocalDateTime.now();
    private OrderStatus orderStatus;

    private Boolean commentStatus;

    private Boolean orderIsGranted;
    private Boolean itemsAreDeliveredToWareHouse;


    private Long userMemberId = SecurityUtils.getLoginUserMemberId();
}
