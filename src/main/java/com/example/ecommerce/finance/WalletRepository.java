package com.example.ecommerce.finance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserMemberId(Long userMemberId);
    List<Wallet> findAllByWalletType(WalletType walletType);

}
