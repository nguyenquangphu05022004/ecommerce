package com.example.ecommerce.system.dal.dataobject.mail;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "sys_mail_mail_template")
public class MailTemplate extends BaseEntity {
    private String name;
    private String title;
    private String content;

    @Convert(converter = JsonListConverter.class)
    private List<String> params;
}
