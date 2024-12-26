package com.example.ecommerce.system.controller.admin.mail.vo.log;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageMailLogReqVO extends PageParam {

    private LocalDateTime start = LocalDateTime.now().minusDays(7);
    private LocalDateTime end = LocalDateTime.now();

}
