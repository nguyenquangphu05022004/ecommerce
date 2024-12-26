package com.example.ecommerce.finance.service.transaction;

import com.example.ecommerce.finance.dal.dataobject.transaction.Transaction;
import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.finance.controller.admin.transaction.vo.PageTransactionReqVO;
import com.example.ecommerce.finance.service.transaction.bo.TransactionCreateReqBO;
import com.example.ecommerce.frame.common.pojo.PageResult;

import java.util.List;

public interface TransactionService {

    PageResult<Transaction> getPageTransactionByUser(Long userMemberId, PageTransactionReqVO req);
    PageResult<Transaction> getPageTransaction(PageTransactionReqVO req);

    List<Transaction> getListTransaction();

    Long createTransaction(TransactionCreateReqBO req);

    Transaction getTransactionById(Long id);

    void updateTransactionStatus(Long id, TransactionStatus transactionStatus);

    List<Transaction> getListTransactionByUserId(Long userId);

}
