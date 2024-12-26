package com.example.ecommerce.finance.controller.admin.wallet.vo;

import com.example.ecommerce.finance.dal.dataobject.wallet.Wallet;
import com.example.ecommerce.finance.enums.WalletType;
import lombok.Data;

@Data
public class WalletRespVO {
    private Long id;
    private String username;
    private Integer amount;
    private WalletType walletType;

    public WalletRespVO(Wallet wallet) {
        this.id = wallet.getId();
        this.username = wallet.getUserMember().getUsername();
        this.amount = wallet.getAmount();
        this.walletType = wallet.getWalletType();
    }
}
