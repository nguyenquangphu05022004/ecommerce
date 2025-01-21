package com.example.ecommerce.finance.controller.admin.transaction.vo;

import com.example.ecommerce.finance.dal.dataobject.transaction.Transaction;
import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.frame.common.date.DateTimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "Doi tuong tra ve - TransactionRespVO")
public class TransactionRespVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "So tien chuyen khoan")
    private Integer amountTransfer;
    @Schema(description = "Chuyen khoan tu username")
    private String fromUsername;
    @Schema(description = "Chuyen toi username")
    private String toUsername;
    @Schema(description = "Ma code")
    private String no;
    private String createdDate;

    @Schema(description = "Noi dung chuyen khoan")
    private String transferContent;
    @Schema(description = "Loi xay ra khi chuyen khoan")
    private String errorMessage;

    @Schema(description = "Tranf thai cua giao dich")
    private TransactionStatus transactionStatus;

    public TransactionRespVO(Transaction transaction) {
        this.id = transaction.getId();
        this.createdDate = DateTimeUtils.format(transaction.getCreatedDate());
        this.amountTransfer = transaction.getAmountTransfer();
        this.fromUsername = transaction.getFromUser().getUsername();
        this.toUsername = transaction.getToUser().getUsername();
        this.no = transaction.getNo();
        this.transferContent = transaction.getTransferContent();
        this.errorMessage = transaction.getErrorMessage();
        this.transactionStatus = transaction.getTransactionStatus();
    }

}
