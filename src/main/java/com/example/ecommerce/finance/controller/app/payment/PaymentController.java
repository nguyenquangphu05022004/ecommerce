package com.example.ecommerce.finance.controller.app.payment;

import com.example.ecommerce.finance.service.payment.chanel.vnpay.VNPayService;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.finance.controller.app.payment.vo.OrderPaymentReqVO;
import com.example.ecommerce.finance.controller.app.payment.vo.PaymentReqVO;
import com.example.ecommerce.finance.controller.app.payment.vo.TransferPaymentReqVO;
import com.example.ecommerce.finance.service.payment.PaymentFactory;
import com.example.ecommerce.finance.service.payment.PaymentType;
import com.example.ecommerce.frame.web.config.WebProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.finance.service.payment.PaymentType.ORDER;
import static com.example.ecommerce.finance.service.payment.PaymentType.TRANSFER;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app-api/finance/payment")
@Tag(name = "Payment")
public class PaymentController {
    private final PaymentFactory paymentFactory;
    private final VNPayService vnPayService;
    private final WebProperties webProperties;
    @PostMapping("/order")
    @Operation(summary = "Thanh toan don hang")
    @ResponseBody
    @ApiResponse(description = "Neu su dung VNPAY se tra ve data: String(Payment Gateway)(Trong CommonResult) nguoc lai Boolean")
    public CommonResult<?> payOrder(@RequestBody OrderPaymentReqVO req) {
        return doPayment(req, ORDER);
    }

    @PostMapping("/transfer")
    @ResponseBody
    @Operation(summary = "Chuyen khoan giua cac user")
    @ApiResponse(description = "Neu su dung VNPAY se tra ve data: String(Payment Gateway)(Trong CommonResult) nguoc lai Boolean")
    public CommonResult<?> transfer(@RequestBody TransferPaymentReqVO req) {
        return doPayment(req, TRANSFER);
    }


    @PermitAll
    @Operation(summary = "Xu ly transaction gui ve tu vnpay")
    @GetMapping("/vnpay-payment")
    public String VNPAYReturn(HttpServletRequest req) {
        vnPayService.orderReturn(req);
        return "redirect:" + webProperties.getLocalDomain() + webProperties.getWalletPath();
    }
    private CommonResult<Object> doPayment(PaymentReqVO req, PaymentType type) {
        return CommonResult.success(this.paymentFactory.paymentService(
                type,
                req.getChanelType()
        ).payment(req));
    }

}
