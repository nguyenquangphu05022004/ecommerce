package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.mail.MailAccountRepository;
import com.example.ecommerce.system.dal.repository.mail.MailTemplateRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import com.example.ecommerce.system.enums.SendMailStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MailSendServiceImplTest extends TestBase {

    @Autowired private MailAccountRepository mailAccountRepository;
    @Autowired private MailTemplateRepository mailTemplateRepository;
    @Autowired private MailSendService mailSendService;
    @Autowired private MailLogService mailLogService;
    @Autowired private UserMemberRepository userMemberRepository;
    @Test
    void sendSingleMail() throws InterruptedException {
        UserMember userMember = buildUser();
        MailAccount mailAccount = buildMail(userMember);
        MailTemplate mailTemplate = mailTemplate();
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "Phu Quang");
        map.put("code", RandomUtils.randomString());
        MailLog mailLog = this.mailSendService.sendSingleMail("quangphu2050@gmail.com",
                userMember.getId(), mailTemplate.getId(), map);

        //cho doi sau khi send mail thanh cong

        Thread.sleep(10000);
        mailLog = this.mailLogService.getMailLogById(mailLog.getId());

        assertEquals(mailLog.getSendMailStatus().name(), SendMailStatus.SUCCESS.name());
    }
    public UserMember buildUser() {
        UserMember userMember = new UserMember();
        this.userMemberRepository.save(userMember);
        return userMember;
    }
    public MailTemplate mailTemplate() {
        MailTemplate mailTemplate = MailTemplate.builder().title("Forget Password")
                .name("Forget password").params(List.of("fullName", "code"))
                .content("Hello, {fullName}. This code: {code} for reset your password")
                .build();
        mailTemplateRepository.save(mailTemplate);
        return mailTemplate;
    }
    public MailAccount buildMail(UserMember userMember) {
        MailAccount account = MailAccount.builder().host("smtp.gmail.com")
                .port(587).auth(true).starttlsEnable(true)
                .username("quangphu2060@gmail.com")
                .userMember(userMember)
                .password("moyu hiyp hfms vttm").build();
        this.mailAccountRepository.save(account);
        return account;
    }
}
