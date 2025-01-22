package com.example.ecommerce.finance.service.transaction;

import com.example.ecommerce.finance.controller.admin.transaction.vo.PageTransactionReqVO;
import com.example.ecommerce.finance.dal.dataobject.transaction.Transaction;
import com.example.ecommerce.finance.dal.repo.transaction.TransactionRepository;
import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.finance.service.transaction.bo.TransactionCreateReqBO;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.finance.enums.ErrorConstants.TRANSACTION_NOT_FOUND;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserMemberService userMemberService;
    @Override
    public PageResult<Transaction> getPageTransactionByUser(Long userMemberId, PageTransactionReqVO req) {
        return new PageResult<>(this.transactionRepository.findAllByUserId(
           userMemberId, req.getStartDateTime(), req.getEndDateTime(), req.buildPageRequest()
        ));
    }

    @Override
    public PageResult<Transaction> getPageTransaction(PageTransactionReqVO req) {
        return new PageResult<>(this.transactionRepository.findAll(
                req.getStartDateTime(), req.getEndDateTime(), req.buildPageRequest()
        ));
    }

    @Override
    public List<Transaction> getListTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public Long createTransaction(TransactionCreateReqBO req) {
        Transaction transaction = Transaction.builder().no(System.currentTimeMillis() + "")
                .amountTransfer(req.getAmountTransfer())
                .transferContent(req.getTransferContent())
                .fromUser(userMemberService.getUserMemberById(req.getFromUserId()))
                .toUser(userMemberService.getUserMemberById(req.getToUserId()))
                .transactionStatus(req.getTransactionStatus())
                .errorMessage(req.getErrorMessage())
                .paymentMode(req.getPaymentMode())
                .build();
        this.transactionRepository.save(transaction);
        return transaction.getId();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> exception(TRANSACTION_NOT_FOUND));
    }

    @Override
    public void updateTransactionStatus(Long id, TransactionStatus transactionStatus) {
        transactionRepository.save(getTransactionById(id).toBuilder()
                .transactionStatus(transactionStatus)
                .build());
    }

    @Override
    public List<Transaction> getListTransactionByUserId(Long userId) {
        return this.transactionRepository.findAllByUserId(userId);
    }
}
