package com.example.ecommerce.system.controller.admin.logger.vo.operation;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageOperationLoggerReqVO extends PageParam {

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

}
