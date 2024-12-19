package com.example.ecommerce.system.dal.repository.mail;

import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MailLogRepository extends JpaRepository<MailLog, Long> {
    List<MailLog> findAllByFromMail(String fromMail);
}
