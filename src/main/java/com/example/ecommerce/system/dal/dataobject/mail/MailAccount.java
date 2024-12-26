package com.example.ecommerce.system.dal.dataobject.mail;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Properties;

@Table(name = "sys_mail_mail_account")
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
public class MailAccount extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_member_id", unique = true)
    private UserMember userMember;

    private String host;
    private Integer port;

    @Column(unique = true)
    private String username;
    private String password;
    private Boolean auth;
    private Boolean starttlsEnable;


    public Properties buildProperties() {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host); //SMTP Host
        props.put("mail.smtp.port", port); //TLS Port
        props.put("mail.smtp.auth", auth); //enable authentication
        props.put("mail.smtp.starttls.enable", starttlsEnable); //enable STARTTLS
        return props;
    }

    public Authenticator buildAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
    }

}
