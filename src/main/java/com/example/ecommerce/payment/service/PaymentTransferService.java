package com.example.ecommerce.payment.service;

import com.example.ecommerce.payment.controller.vo.TransferPaymentReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.ecommerce.payment.chanel.ParamEnum.*;

@Service
@RequiredArgsConstructor
public class PaymentTransferService extends PaymentService<TransferPaymentReqVO> {

    @Override
    public Object payment(TransferPaymentReqVO transferPaymentReqVO) {
        Map<String, Object> map = new HashMap<>();
        map.put(CONTENT, transferPaymentReqVO.getContent());
        map.put(FROM_USER_ID, transferPaymentReqVO.getFromUserId());
        map.put(TO_USER_ID, transferPaymentReqVO.getToUserId());
        map.put(AMOUNT, transferPaymentReqVO.getTransferAmount());
        return this.paymentChannel.doPayment(map);
    }
}
