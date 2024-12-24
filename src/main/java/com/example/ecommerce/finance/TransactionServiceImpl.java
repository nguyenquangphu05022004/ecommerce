package com.example.ecommerce.finance;

import com.example.ecommerce.finance.vo.PageTransactionReqVO;
import com.example.ecommerce.finance.vo.TransactionCreateReqVO;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.finance.ErrorConstants.TRANSACTION_NOT_FOUND;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final UserMemberService userMemberService;
    @Override
    public PageResult<Transaction> getPageTransactionByUser(Long userMemberId, PageTransactionReqVO req) {
        return null;
    }

    @Override
    public PageResult<Transaction> getPageTransaction(PageTransactionReqVO req) {
        return null;
    }

    @Override
    public Long createTransaction(TransactionCreateReqVO req) {
        Transaction transaction = Transaction.builder().no(System.currentTimeMillis() + "")
                .amountTransfer(req.getAmountTransfer())
                .transferContent(req.getTransferContent())
                .fromUser(userMemberService.getUserMemberById(req.getFromUserId()))
                .toUser(userMemberService.getUserMemberById(req.getToUserId()))
                .transactionStatus(req.getTransactionStatus())
                .errorMessage(null)
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
}
