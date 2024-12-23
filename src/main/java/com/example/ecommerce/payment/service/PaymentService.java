package com.example.ecommerce.payment.service;

import com.example.ecommerce.payment.chanel.PaymentChannel;
import com.example.ecommerce.payment.constants.ChanelType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentService {

    @Setter
    private PaymentChannel paymentChannel;

    public abstract void payment(Long fromUser, Long payObjectId);
}
