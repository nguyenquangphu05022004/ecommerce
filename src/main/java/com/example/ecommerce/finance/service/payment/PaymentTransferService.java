package com.example.ecommerce.finance.service.payment;

import com.example.ecommerce.finance.controller.app.payment.vo.TransferPaymentReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.ecommerce.finance.enums.ParamEnum.*;

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
