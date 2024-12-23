package com.example.ecommerce.payment.chanel;

import com.example.ecommerce.payment.service.PaymentType;

public interface PaymentChannel {

    default void doPayment(PaymentType paymentType, Object... params) {}

}
