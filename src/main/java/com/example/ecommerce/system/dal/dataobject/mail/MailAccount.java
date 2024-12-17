package com.example.ecommerce.system.dal.dataobject.mail;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "sys_mail_mail_account")
@Entity
public class MailAccount extends BaseEntity {
    private String host;
    private String port;
    private String username;
    private String password;
    private Boolean auth;
    private Boolean starttlsEnable;
}
