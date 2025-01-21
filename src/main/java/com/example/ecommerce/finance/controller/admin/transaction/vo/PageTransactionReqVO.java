package com.example.ecommerce.finance.controller.admin.transaction.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(name = "Yeu cau phan chia trang - PageTransactionReqVO")
public class PageTransactionReqVO extends PageParam {

    @Schema(description = "Tim kiem tu ngay", example = "05-02-2004")
    private LocalDateTime startDateTime =LocalDateTime.now().minusDays(7);
    @Schema(description = "Tim kiem den ngay", example = "20-02-2004")
    private LocalDateTime endDateTime = LocalDateTime.now();

}
