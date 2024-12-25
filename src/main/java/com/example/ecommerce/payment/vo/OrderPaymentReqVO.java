package com.example.ecommerce.payment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Yeu cau thanh toan don hang - OrderPaymentReqVO")
public class OrderPaymentReqVO extends PaymentReqVO {
    @Schema(description = "Ma don hang", example = "555", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "orderId can't be null")
    private Long orderId;
}
