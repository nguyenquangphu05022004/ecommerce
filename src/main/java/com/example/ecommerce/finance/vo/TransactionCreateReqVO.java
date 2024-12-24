package com.example.ecommerce.finance.vo;

import com.example.ecommerce.finance.TransactionStatus;
import lombok.Data;

@Data
public class TransactionCreateReqVO {
    private Integer amountTransfer;
    private Long fromUserId;
    private Long toUserId;
    private String no;
    private String transferContent;
    private TransactionStatus transactionStatus;
    private String errorMessage;
}
