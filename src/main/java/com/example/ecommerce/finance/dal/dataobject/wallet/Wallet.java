package com.example.ecommerce.finance.dal.dataobject.wallet;

import com.example.ecommerce.finance.enums.WalletType;
import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import static com.example.ecommerce.finance.enums.ErrorConstants.AMOUNT_NOT_ENOUGH;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

@Entity
@Table(name = "finance_wallet")
@SuperBuilder(toBuilder = true)
@Getter
public class Wallet extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id", unique = true)
    private UserMember userMember;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    public void topUp(Integer money) {
        this.amount += money;
    }

    public void withDraw(Integer money) {
        if(this.amount < money) {
            throw exception(AMOUNT_NOT_ENOUGH);
        }
        this.amount -= money;
    }
}
