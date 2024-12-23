package com.example.ecommerce.finance.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PageTransactionReqVO extends PageParam {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
