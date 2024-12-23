package com.example.ecommerce.finance;

import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ecommerce.finance.ErrorConstants.WALLET_NOT_FOUND;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

@RequiredArgsConstructor
@Service
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    @Override
    public void topUpToWallet(Long userMemberId, Integer amount) {
        Wallet wallet = getWalletByUserId(userMemberId);
        wallet.topUp(amount);
        this.walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletByUserId(Long userMemberId) {
        Wallet wallet = this.walletRepository.findByUserMemberId(userMemberId)
                .orElse(null);
        if(wallet == null) {
            wallet = Wallet.builder().amount(10_000_000)
                    .userMember(UserMember.builder().id(userMemberId).build())
                    .walletType(WalletType.USER_MEMBER)
                    .build();
            this.walletRepository.save(wallet);
        }
        return wallet;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void withdrawFromWalletToAnotherWallet(Long fromUserId, Long walletId, Integer amount) {
        Wallet fromWallet = getWalletByUserId(fromUserId);
        Wallet toWallet = getWalletById(walletId);

        fromWallet.withDraw(amount);
        toWallet.topUp(amount);

        this.walletRepository.save(fromWallet);
        this.walletRepository.save(toWallet);
    }

    @Override
    public Wallet getWalletById(Long walletId) {
        return this.walletRepository.findById(walletId)
                .orElseThrow(() -> exception(WALLET_NOT_FOUND));
    }

    @Override
    public Wallet getWalletByWalletType(WalletType walletType) {
        return this.walletRepository.findAllByWalletType(walletType)
                .stream().findFirst()
                .orElseThrow(() -> exception(WALLET_NOT_FOUND));
    }
}
