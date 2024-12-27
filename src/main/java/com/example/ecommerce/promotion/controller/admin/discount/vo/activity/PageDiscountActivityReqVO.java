package com.example.ecommerce.promotion.controller.admin.discount.vo.activity;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageDiscountActivityReqVO extends PageParam {
    private LocalDateTime start;
    private LocalDateTime end;
}
