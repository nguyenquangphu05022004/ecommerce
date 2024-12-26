package com.example.ecommerce.finance.service.wallet;

import com.example.ecommerce.finance.controller.admin.wallet.vo.PageWalletReqVO;
import com.example.ecommerce.finance.dal.dataobject.wallet.Wallet;
import com.example.ecommerce.finance.enums.WalletType;
import com.example.ecommerce.frame.common.pojo.PageResult;

public interface WalletService {


    void topUpToWallet(Long userMemberId, Integer amount);
    Wallet getWalletByUserId(Long userMemberId);
    void withdrawFromWalletToAnotherWallet(Long fromUserId, Long toUserId, Integer amount);
    Wallet getWalletById(Long walletId);
    Wallet getWalletByWalletType(WalletType walletType);

    PageResult<Wallet> getPageWallet(PageWalletReqVO req);
}
