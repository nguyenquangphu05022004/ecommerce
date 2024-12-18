package com.example.ecommerce.system.dal.repository.mail;

import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
}
