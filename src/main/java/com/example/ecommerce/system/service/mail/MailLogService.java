package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import com.example.ecommerce.system.enums.SendMailStatus;

import java.util.List;
import java.util.Map;

public interface MailLogService {
    MailLog createMailLog(String fromMail, String toMail, String  content, String title);
    MailLog getMailLogById(Long mailLogId);
    List<MailLog> getListMailLog(Long userId);
    void deleteMailLog(Long id);

}
