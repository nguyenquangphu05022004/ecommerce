package com.example.ecommerce.finance.service.payment;

import com.example.ecommerce.finance.service.payment.chanel.app.AppPayService;
import com.example.ecommerce.finance.service.payment.chanel.vnpay.VNPayService;
import com.example.ecommerce.finance.enums.ChanelType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFactory {
    private final ApplicationContext context;
    public PaymentService paymentService(PaymentType paymentType,
                                         ChanelType chanelType) {
        PaymentService paymentService = switch (paymentType) {
            case ORDER -> context.getBean(PaymentOrderService.class);
            case TRANSFER -> context.getBean(PaymentTransferService.class);
        };
        paymentService.setPaymentChannel(switch (chanelType) {
            case APP -> context.getBean(AppPayService.class);
            case VNPAY -> context.getBean(VNPayService.class);
        });
        return paymentService;
    }
}
