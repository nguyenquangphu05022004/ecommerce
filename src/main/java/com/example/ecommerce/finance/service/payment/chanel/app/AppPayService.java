package com.example.ecommerce.finance.service.payment.chanel.app;

import com.example.ecommerce.finance.service.transaction.TransactionService;
import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.finance.service.wallet.WalletService;
import com.example.ecommerce.finance.service.transaction.bo.TransactionCreateReqBO;
import com.example.ecommerce.finance.service.payment.chanel.PaymentChannel;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.trade.enums.PaymentMode;
import com.example.ecommerce.trade.enums.PaymentStatus;
import com.example.ecommerce.trade.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.example.ecommerce.finance.enums.ParamEnum.*;

@Service
@RequiredArgsConstructor
public class AppPayService implements PaymentChannel {
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final OrderService orderService;
    @Override
    public Object doPayment(Map<String, Object> params) {
        TransactionCreateReqBO req = new TransactionCreateReqBO();
        req.setNo(System.currentTimeMillis() +"");
        req.setTransferContent((String) params.get(CONTENT));
        req.setToUserId((Long) params.get(TO_USER_ID));
        req.setFromUserId((Long) params.get(FROM_USER_ID));
        req.setAmountTransfer((Integer) params.get(AMOUNT));
        req.setPaymentMode(PaymentMode.APP);
        Long orderId = (Long) params.get(ORDER_ID);
        try {
            walletService.withdrawFromWalletToAnotherWallet(
                    req.getFromUserId(), req.getToUserId(), req.getAmountTransfer()
            );
            req.setTransactionStatus(TransactionStatus.SUCCESS);
            orderService.updatePaymentStatus(orderId, PaymentStatus.SUCCESS);
        } catch (Exception e) {
            req.setTransactionStatus(TransactionStatus.FAILED);
            req.setErrorMessage(e.getMessage());
        } finally {
            transactionService.createTransaction(req);
        }
        return true;
    }
}
