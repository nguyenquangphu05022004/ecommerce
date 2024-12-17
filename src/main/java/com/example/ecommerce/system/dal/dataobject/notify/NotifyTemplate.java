package com.example.ecommerce.system.dal.dataobject.notify;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "sys_notify_notify_template")
public class NotifyTemplate extends BaseEntity {
    private String name;
    private String content;
    @Convert(converter = JsonListConverter.class)
    private List<String> params;
}
