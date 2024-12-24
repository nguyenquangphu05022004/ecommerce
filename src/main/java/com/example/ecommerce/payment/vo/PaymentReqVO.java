package com.example.ecommerce.payment.vo;

import com.example.ecommerce.payment.constants.ChanelType;
import com.example.ecommerce.payment.service.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentReqVO {
    /**
     * @link: SecurityUtils.getLoginUserId()
     */
    @NotNull(message = "sender can't be null")
    private Long fromUserId;
    @NotNull(message = "chanel type can't be null")
    private ChanelType chanelType;

    private String content;

}
