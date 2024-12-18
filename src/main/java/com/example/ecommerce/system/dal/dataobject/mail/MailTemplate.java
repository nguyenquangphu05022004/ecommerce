package com.example.ecommerce.system.dal.dataobject.mail;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "sys_mail_mail_template")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MailTemplate extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String title;
    private String content;

    @Convert(converter = JsonListConverter.class)
    private List<String> params;
}
