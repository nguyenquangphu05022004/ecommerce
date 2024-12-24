package com.example.ecommerce.finance;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
public class Transaction extends BaseEntity {
    private Integer amountTransfer;
    private UserMember fromUser;
    private UserMember toUser;
    private String no;

    private String transferContent;
    private String errorMessage;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
