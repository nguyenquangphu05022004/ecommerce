package com.example.ecommerce.system.controller.admin.mail.vo.account;

import com.example.ecommerce.system.dal.dataobject.mail.MailAccount;
import lombok.Data;

@Data
public class MailAccountRespVO {
    private Long id;
    private String host ;
    private Integer port;
    private String username;
    private String password;
    private Boolean auth ;
    private Boolean starttlsEnable;

    public MailAccountRespVO(MailAccount account) {
        this.id = account.getId();
        this.host = account.getHost();
        this.port = account.getPort();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.auth = account.getAuth();
        this.starttlsEnable = account.getStarttlsEnable();
    }
}
