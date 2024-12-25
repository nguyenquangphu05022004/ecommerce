package com.example.ecommerce.payment.controller;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.payment.service.PaymentFactory;
import com.example.ecommerce.payment.service.PaymentType;
import com.example.ecommerce.payment.vo.OrderPaymentReqVO;
import com.example.ecommerce.payment.vo.PaymentReqVO;
import com.example.ecommerce.payment.vo.TransferPaymentReqVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ecommerce.payment.service.PaymentType.ORDER;
import static com.example.ecommerce.payment.service.PaymentType.TRANSFER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/payment")
@Tag("Payment")
public class PaymentController {
    private final PaymentFactory paymentFactory;

    @PostMapping("/order")
    @Operation(summary = "Thanh toan don hang")

    public CommonResult<?> payOrder(@RequestBody OrderPaymentReqVO req) {
        return doPayment(req, ORDER);
    }

    @PostMapping("/transfer")
    @Operation(summary = "Chuyen khoan giua cac user")
    public CommonResult<?> transfer(@RequestBody TransferPaymentReqVO req) {
        return doPayment(req, TRANSFER);
    }

    private CommonResult<Object> doPayment(PaymentReqVO req, PaymentType type) {
        return CommonResult.success(this.paymentFactory.paymentService(
                type,
                req.getChanelType()
        ).payment(req));
    }

}
