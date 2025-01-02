package com.example.ecommerce.system.controller.admin.user.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageUserReqVO extends PageParam {
    private Boolean locked;
    private LocalDateTime start;
    private LocalDateTime end;
}
