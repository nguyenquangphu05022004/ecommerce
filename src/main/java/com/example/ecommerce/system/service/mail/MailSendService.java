package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.system.enums.UserType;

import java.util.Map;

public interface MailSendService {
    void sendSingleMailToUserAdmin(String toMail, Long fromUserId,
                                   Long mailTemplateId, Map<String, Object> templateParams);
    void sendSingleMailToUserMember(String toMail, Long fromUserId,
                              Long mailTemplateId, Map<String, Object> templateParams);

    void sendSingleMail(String toMail, Long fromUserId, UserType userType,
                        Long mailTemplateId, Map<String, Object> templateParams);
}
