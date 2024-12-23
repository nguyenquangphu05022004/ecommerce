package com.example.ecommerce.payment.chanel.app;

import com.example.ecommerce.finance.Wallet;
import com.example.ecommerce.finance.WalletService;
import com.example.ecommerce.payment.chanel.PaymentChannel;
import com.example.ecommerce.payment.service.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppPayService implements PaymentChannel {
    private final WalletService walletService;

    @Override
    public void doPayment(PaymentType paymentType, Object... params) {
        PaymentChannel.super.doPayment(paymentType, params);
    }
}
