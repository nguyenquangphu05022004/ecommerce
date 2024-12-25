package com.example.ecommerce.system.controller.admin.mail.vo.account;

import lombok.Data;

@Data
public class MailAccountCreateReqVO {
    private String host = "smtp.gmail.com";
    private Integer port = 587;
    private String username;
    private String password;
    private Boolean auth = true;
    private Boolean starttlsEnable = true;
}
