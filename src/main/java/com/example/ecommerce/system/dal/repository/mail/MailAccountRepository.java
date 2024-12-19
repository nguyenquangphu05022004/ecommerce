package com.example.ecommerce.system.dal.repository.mail;

import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailAccountRepository extends JpaRepository<MailAccount, Long> {
    Optional<MailAccount> findByUserMemberId(Long userMemberId);
    Optional<MailAccount> findByUsername(String username);
}
