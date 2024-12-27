package com.example.ecommerce.finance.service.payment;

import com.example.ecommerce.finance.service.payment.chanel.PaymentChannel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentService<REQUEST> {

    @Setter
    protected PaymentChannel paymentChannel;

    public abstract Object payment(REQUEST request);
}
