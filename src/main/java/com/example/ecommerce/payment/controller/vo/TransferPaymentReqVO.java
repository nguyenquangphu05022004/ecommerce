package com.example.ecommerce.payment.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Schema(name = "Yeu cau chuyen khoan - TransferPaymentReqVO")
public class TransferPaymentReqVO extends PaymentReqVO{
    @Schema(description = "Nguoi nhan tien", example = "44", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long toUserId;

    @Schema(description = "So tien chuyen", example = "15000", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 5000, message = "Your amount must greater than 5000")
    private Integer transferAmount;
}
