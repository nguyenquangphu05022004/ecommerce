package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.frame.common.mail.MailUtils;
import com.example.ecommerce.mq.message.mail.MailMessage;
import com.example.ecommerce.mq.producer.MailProducer;
import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import com.example.ecommerce.system.dal.repository.mail.MailLogRepository;
import com.example.ecommerce.system.enums.SendMailStatus;
import com.example.ecommerce.system.enums.SysErrorCodeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService{
    private final MailAccountService mailAccountService;
    private final MailTemplateService mailTemplateService;
    private final MailLogService mailLogService;
    private final MailProducer mailProducer;
    private final MailLogRepository mailLogRepository;
    @Override
    public MailLog sendSingleMail(String toMail, Long fromUserId,
                               Long mailTemplateId, Map<String, Object> templateParams) {
        MailAccount mailAccount = this.mailAccountService.getMailAccountByUserId(fromUserId);
        MailTemplate mailTemplate = this.mailTemplateService.getMailTemplateById(mailTemplateId);
        validParam(mailTemplate, templateParams);
        MailLog mailLog = mailLogService.createMailLog(
                mailAccount.getUsername(), toMail,
                StringUtils.formatContent(mailTemplate.getContent(), templateParams),
                StringUtils.formatContent(mailTemplate.getTitle(), templateParams)
        );
        this.mailProducer.sendMail(mailAccount.getUsername(), toMail, mailLog.getId());
        return mailLog;
    }

    private void validParam(MailTemplate mailTemplate, Map<String, Object> templateParams) {
        for(String param : mailTemplate.getParams()) {
            if(!templateParams.containsKey(param)) {
                throw exception(SysErrorCodeConstants.MAIL_TEMPLATE_PARAMS_MISSING_KEY);
            }
        }
    }

    @Override
    public void doSendMail(MailMessage mailMessage) {
        MailAccount mailAccount = this.mailAccountService.getMailAccountByUsername(mailMessage.getFromMail());
        MailLog mailLog = mailLogService.getMailLogById(mailMessage.getMailLogId());
        try {
            MailUtils.sendEmail(mailAccount,mailLog.getTitle(), mailLog.getContent(), mailLog.getToMail());
            mailLog.setSendMailStatus(SendMailStatus.SUCCESS);
            System.out.println("----------------->send mail ok<------------------");
        } catch (Exception e) {
            mailLog.setSendMailStatus(SendMailStatus.FAILED);
            System.out.println("----------------->send mail error<------------------");
           e.printStackTrace();
        } finally {
            this.mailLogRepository.save(mailLog);
        }
    }
}
