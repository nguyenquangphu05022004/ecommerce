package com.example.ecommerce.system.dal.dataobject.notify;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonMapConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "sys_notify_notify_message")
public class NotifyMessage extends BaseEntity {
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "notify_template_id")
    private NotifyTemplate notifyTemplate;

    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> templateParams;

    private Boolean readStatus;
    private LocalDateTime readTime;

}
