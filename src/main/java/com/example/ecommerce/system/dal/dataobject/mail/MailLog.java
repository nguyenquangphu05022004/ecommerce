package com.example.ecommerce.system.dal.dataobject.mail;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.enums.SendMailStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sys_mail_mail_log")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class MailLog extends BaseEntity {
    private String fromMail;
    private String toMail;
    private String content;
    private String title;
    @Enumerated(EnumType.STRING)
    private SendMailStatus sendMailStatus;
}
