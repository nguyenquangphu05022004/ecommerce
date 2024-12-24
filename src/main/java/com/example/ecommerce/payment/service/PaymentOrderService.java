package com.example.ecommerce.payment.service;

import com.example.ecommerce.finance.Wallet;
import com.example.ecommerce.finance.WalletService;
import com.example.ecommerce.finance.WalletType;
import com.example.ecommerce.payment.vo.OrderPaymentReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.ecommerce.payment.chanel.ParamEnum.*;

@Service
@RequiredArgsConstructor
public class PaymentOrderService extends PaymentService<OrderPaymentReqVO>{

    private final OrderService orderService;
    private final WalletService walletService;

    @Override
    public Object payment(OrderPaymentReqVO req) {
        Order order = this.orderService.getOrderById(req.getOrderId());
        if(!order.getUserMember().getId().equals(req.getFromUserId())) {
            throw new RuntimeException(String.format("Order has id: %s not match with user has id: %s", order.getId(), req.getFromUserId()));
        }
        /**
         * Khi thanh toan thi se chuyen truc tiep cho he thong(khong qua seller).
         * He thong se danh gia va se chuyen cho seller sau.
         */
        Wallet wallet = walletService.getWalletByWalletType(WalletType.SYSTEM);

        Map<String, Object> params = new HashMap<>();
        params.put(ORDER_ID, order.getId());
        params.put(FROM_USER_ID, req.getFromUserId());
        params.put(TO_USER_ID, wallet.getUserMember().getId());
        params.put(CONTENT, req.getContent());

        this.paymentChannel.doPayment(params);
    }
}
