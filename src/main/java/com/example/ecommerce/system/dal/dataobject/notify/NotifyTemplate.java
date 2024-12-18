package com.example.ecommerce.system.dal.dataobject.notify;

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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "sys_notify_notify_template")
@Getter
public class NotifyTemplate extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String content;
    @Convert(converter = JsonListConverter.class)
    private List<String> params;
}
