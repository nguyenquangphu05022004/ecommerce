package com.example.ecommerce.finance.controller.admin.transaction;

import com.example.ecommerce.finance.controller.admin.transaction.vo.PageTransactionReqVO;
import com.example.ecommerce.finance.controller.admin.transaction.vo.TransactionRespVO;
import com.example.ecommerce.finance.dal.dataobject.transaction.Transaction;
import com.example.ecommerce.finance.service.transaction.TransactionService;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "Giao Dich - Transaction")
@RequestMapping("/admin-api/finance/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Lay toan bo cac giao dich cua cac user")
    public CommonResult<List<TransactionRespVO>> getList() {
        List<Transaction> transaction = this.transactionService.getListTransaction();
        return CommonResult.success(CollUtils.convertList(transaction, TransactionRespVO::new));
    }

    @GetMapping("/page")
    @Operation(summary = "Lay toan bo cac giao dich cua cac user, phan chia trang")
    public CommonResult<PageResult<TransactionRespVO>> getPageTransaction(@RequestBody PageTransactionReqVO req) {
        PageResult<Transaction> transaction = this.transactionService.getPageTransaction(req);
        return CommonResult.success(transaction, TransactionRespVO::new);
    }

    @GetMapping("/my-transactions/page")
    @Operation(summary = "Lay toan bo cac giao dich cua user hien tai, phan chia trang")
    public CommonResult<PageResult<TransactionRespVO>> getMyPageTransaction(@RequestBody PageTransactionReqVO req) {
        PageResult<Transaction> transaction = this.transactionService.getPageTransactionByUser(SecurityUtils.getLoginUserMemberId(), req);
        return CommonResult.success(transaction, TransactionRespVO::new);
    }

    @GetMapping("/my-transactions")
    @Operation(summary = "Lay toan bo cac giao dich cua nguoi dung hien tai")
    public CommonResult<List<TransactionRespVO>> getMyListTransaction() {
        List<Transaction> transaction = this.transactionService.getListTransactionByUserId(SecurityUtils.getLoginUserMemberId());
        return CommonResult.success(CollUtils.convertList(transaction, TransactionRespVO::new));
    }


}
