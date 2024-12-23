package com.example.ecommerce.finance;

import com.example.ecommerce.finance.vo.PageTransactionReqVO;
import com.example.ecommerce.finance.vo.TransactionCreateReqVO;
import com.example.ecommerce.frame.common.pojo.PageResult;

public interface TransactionService {

    PageResult<Transaction> getPageTransactionByUser(Long userMemberId, PageTransactionReqVO req);
    PageResult<Transaction> getPageTransaction(PageTransactionReqVO req);

    Transaction createTransaction(TransactionCreateReqVO req);

    Transaction getTransactionById(Long id);

    void updateTransactionStatus(Long id, TransactionStatus transactionStatus);
}
