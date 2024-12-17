package com.example.ecommerce.system.dal.dataobject.mail;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import com.example.ecommerce.frame.common.converter.JsonMapConverter;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "sys_mail_mail_log")
public class MailLog extends BaseEntity {
    private String fromEmail;
    private String toEmail;

    @ManyToOne
    @JoinColumn(name = "mail_template_id")
    private MailTemplate mailTemplate;
    @Convert(converter = JsonListConverter.class)
    private Map<String, Object> templateParams;

}
