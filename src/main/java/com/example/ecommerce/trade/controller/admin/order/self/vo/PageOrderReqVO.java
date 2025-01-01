package com.example.ecommerce.trade.controller.admin.order.self.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class PageOrderReqVO extends PageParam {
    private LocalDateTime start;
    private LocalDateTime end;
    private Map<String, String> condition;
}
