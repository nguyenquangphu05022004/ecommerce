package com.example.ecommerce.system.controller.admin.mail.vo.template;

import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import lombok.Data;

@Data
public class MailTemplateSimpleRespVO {
    private Long id;
    private String name;

    public MailTemplateSimpleRespVO(MailTemplate mailTemplate) {
        this.id = mailTemplate.getId();
        this.name = mailTemplate.getName();
    }
}
