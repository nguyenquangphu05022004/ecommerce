package com.example.ecommerce.system.controller.admin.logger.vo.operation;

import com.example.ecommerce.frame.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageOperationLoggerReqVO extends PageParam {

    @Schema(description = "Bat dau tu ngay")
    private LocalDateTime fromDate = LocalDateTime.now().minusDays(7);
    @Schema(description = "Toi ngay")
    private LocalDateTime toDate = LocalDateTime.now();

}
