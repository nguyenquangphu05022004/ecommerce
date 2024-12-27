package com.example.ecommerce.finance.controller.app.payment;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.finance.controller.app.payment.vo.OrderPaymentReqVO;
import com.example.ecommerce.finance.controller.app.payment.vo.PaymentReqVO;
import com.example.ecommerce.finance.controller.app.payment.vo.TransferPaymentReqVO;
import com.example.ecommerce.finance.service.payment.PaymentFactory;
import com.example.ecommerce.finance.service.payment.PaymentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ecommerce.finance.service.payment.PaymentType.ORDER;
import static com.example.ecommerce.finance.service.payment.PaymentType.TRANSFER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/finance/payment")
@Tag(name = "Payment")
public class PaymentController {
    private final PaymentFactory paymentFactory;

    @PostMapping("/order")
    @Operation(summary = "Thanh toan don hang")
    @ApiResponse(description = "Neu su dung VNPAY se tra ve data: String(Payment Gateway)(Trong CommonResult) nguoc lai Boolean")
    public CommonResult<?> payOrder(@RequestBody OrderPaymentReqVO req) {
        return doPayment(req, ORDER);
    }

    @PostMapping("/transfer")
    @Operation(summary = "Chuyen khoan giua cac user")
    @ApiResponse(description = "Neu su dung VNPAY se tra ve data: String(Payment Gateway)(Trong CommonResult) nguoc lai Boolean")
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
