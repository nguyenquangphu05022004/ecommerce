package com.example.ecommerce.finance.dal.repo.wallet;

import com.example.ecommerce.finance.dal.dataobject.wallet.Wallet;
import com.example.ecommerce.finance.enums.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserMemberId(Long userMemberId);
    List<Wallet> findAllByWalletType(WalletType walletType);

}
