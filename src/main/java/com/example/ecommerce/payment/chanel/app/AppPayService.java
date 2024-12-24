package com.example.ecommerce.payment.chanel.app;

import com.example.ecommerce.finance.TransactionService;
import com.example.ecommerce.finance.TransactionStatus;
import com.example.ecommerce.finance.WalletService;
import com.example.ecommerce.finance.vo.TransactionCreateReqVO;
import com.example.ecommerce.frame.common.pojo.CommonResult;
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
        TransactionCreateReqVO req = new TransactionCreateReqVO();
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
