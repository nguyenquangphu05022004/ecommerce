package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.dal.repository.mail.MailLogRepository;
import com.example.ecommerce.system.enums.SendMailStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.MAIL_LOG_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MailLogServiceImpl implements MailLogService{
    private final MailLogRepository mailLogRepository;
    private final MailAccountService mailAccountService;
    @Override
    public MailLog createMailLog(String fromMail, String toMail, String  content, String title) {
        MailLog mailLog = MailLog.builder().fromMail(fromMail)
                .toMail(toMail).content(content).sendMailStatus(SendMailStatus.PROCESSING)
                .title(title).build();
        this.mailLogRepository.save(mailLog);
        return mailLog;
    }

    @Override
    public MailLog getMailLogById(Long mailLogId) {
        return this.mailLogRepository.findById(mailLogId)
                .orElseThrow(() -> exception(MAIL_LOG_NOT_FOUND));
    }

    @Override
    public List<MailLog> getListMailLog(Long userId) {
        MailAccount mailAccount = this.mailAccountService.getMailAccountByUserId(userId);
        return this.mailLogRepository.findAllByFromMail(mailAccount.getUsername());
    }

    @Override
    public void deleteMailLog(Long id) {
        this.mailLogRepository.deleteById(id);
    }
}
