package com.example.ecommerce.finance.service.transaction.bo;

import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.trade.enums.PaymentMode;
import lombok.Data;

@Data
public class TransactionCreateReqBO {
    private Integer amountTransfer;
    private Long fromUserId;
    private Long toUserId;
    private String no;
    private String transferContent;
    private TransactionStatus transactionStatus;
    private String errorMessage;
    private PaymentMode paymentMode;
}
