package com.example.ecommerce.finance.dal.dataobject.transaction;

import com.example.ecommerce.finance.enums.TransactionStatus;
import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Table(name = "finance_transaction")
@Entity
@NoArgsConstructor
public class Transaction extends BaseEntity {
    private Integer amountTransfer;
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private UserMember fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private UserMember toUser;
    private String no;

    private String transferContent;
    private String errorMessage;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
