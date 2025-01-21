package com.example.ecommerce.finance.controller.app.payment.vo;

import com.example.ecommerce.finance.enums.ChanelType;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentReqVO {
    /**
     * @link: SecurityUtils.getLoginUserId()
     */
    @NotNull(message = "sender can't be null")
    @Schema(description = "Nguoi gui", example = "2")
    private Long fromUserId = SecurityUtils.getLoginUserMemberId();
    @NotNull(message = "chanel type can't be null")
    @Schema(description = "Kenh thanh toan", example = "APP", requiredMode = Schema.RequiredMode.REQUIRED)
    private ChanelType chanelType;

    @Schema(description = "Noi thanh chuyen khoan", example = "Thanh toan don hang")
    private String content;

}
