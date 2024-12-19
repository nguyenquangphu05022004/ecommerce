package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.mq.message.mail.MailMessage;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.enums.SendMailStatus;
import com.example.ecommerce.system.enums.UserType;

import java.util.Map;

public interface MailSendService {
    MailLog sendSingleMail(String toMail, Long fromUserId,
                           Long mailTemplateId, Map<String, Object> templateParams);
    void doSendMail(MailMessage mailMessage) throws Exception;
}
