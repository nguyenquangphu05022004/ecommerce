package com.example.ecommerce.finance;

public interface WalletService {


    void topUpToWallet(Long userMemberId, Integer amount);
    Wallet getWalletByUserId(Long userMemberId);
    void withdrawFromWalletToAnotherWallet(Long fromUserId, Long toUserId, Integer amount);
    Wallet getWalletById(Long walletId);
    Wallet getWalletByWalletType(WalletType walletType);
}
