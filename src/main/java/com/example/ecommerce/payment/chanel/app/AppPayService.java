package com.example.ecommerce.payment.chanel.app;

import com.example.ecommerce.finance.service.transaction.TransactionService;
import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.finance.service.wallet.WalletService;
import com.example.ecommerce.finance.service.transaction.bo.TransactionCreateReqBO;
import com.example.ecommerce.payment.chanel.PaymentChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.ecommerce.payment.chanel.ParamEnum.*;

@Service
@RequiredArgsConstructor
public class AppPayService implements PaymentChannel {
    private final WalletService walletService;
    private final TransactionService transactionService;
    @Override
    public Object doPayment(Map<String, Object> params) {
        TransactionCreateReqBO req = new TransactionCreateReqBO();
        req.setNo(System.currentTimeMillis() +"");
        req.setTransferContent((String) params.get(CONTENT));
        req.setToUserId((Long) params.get(TO_USER_ID));
        req.setFromUserId((Long) params.get(FROM_USER_ID));
        req.setAmountTransfer((Integer) params.get(AMOUNT));
        try {
            walletService.withdrawFromWalletToAnotherWallet(
                    req.getFromUserId(), req.getToUserId(), req.getAmountTransfer()
            );
            req.setTransactionStatus(TransactionStatus.SUCCESS);
        } catch (Exception e) {
            req.setTransactionStatus(TransactionStatus.FAILED);
            req.setErrorMessage(e.getMessage());
        } finally {
            transactionService.createTransaction(req);
        }
        return true;
    }
}
