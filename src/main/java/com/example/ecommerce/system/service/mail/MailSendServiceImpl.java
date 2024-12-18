package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService{
    @Override
    public void sendSingleMailToUserAdmin(String toMail, Long fromUserId,
                                          Long mailTemplateId, Map<String, Object> templateParams) {

    }

    @Override
    public void sendSingleMailToUserMember(String toMail, Long fromUserId,
                                           Long mailTemplateId, Map<String, Object> templateParams) {

    }

    @Override
    public void sendSingleMail(String toMail, Long fromUserId,
                               UserType userType, Long mailTemplateId, Map<String, Object> templateParams) {

    }
}
