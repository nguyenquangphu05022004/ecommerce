package com.example.ecommerce.payment.service;

import com.example.ecommerce.finance.*;
import com.example.ecommerce.finance.vo.TransactionCreateReqVO;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentOrderService extends PaymentService{

    private final OrderService orderService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    @Override
    public void payment(Long fromUser, Long orderId) {
        Order order = this.orderService.getOrderById(orderId);
        Wallet wallet = this.walletService.getWalletByWalletType(WalletType.SYSTEM);
        TransactionCreateReqVO req = new TransactionCreateReqVO();
        req.setNo(System.currentTimeMillis() + ""); req.setAmountTransfer(order.totalPrice());
        req.setFromUserId(fromUser); req.setToUserId(wallet.getUserMember().getId());
        req.setTransferContent(String.format("Payment Order with No: %s", order.getNo()));
        try {
            walletService.withdrawFromWalletToAnotherWallet(fromUser, wallet.getUserMember().getId(), order.totalPrice());
            req.setTransactionStatus(TransactionStatus.SUCCESS);
        } catch (ServiceException ex) {
            req.setTransactionStatus(TransactionStatus.FAILED);
        } finally {
            this.transactionService.createTransaction(req);
        }

    }
}
