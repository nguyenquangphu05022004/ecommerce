package com.example.ecommerce.payment.vo;

import lombok.Data;

@Data
public class TransferPaymentReqVO extends PaymentReqVO{
    private Long toUserId;
    private Integer transferAmount;
}
