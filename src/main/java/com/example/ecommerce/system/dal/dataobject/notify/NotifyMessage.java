package com.example.ecommerce.system.dal.dataobject.notify;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonMapConverter;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "sys_notify_notify_message")
@Getter
public class NotifyMessage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;
    @ManyToOne
    @JoinColumn(name = "notify_template_id")
    private NotifyTemplate notifyTemplate;

    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> templateParams;

    private Boolean readStatus;
    private LocalDateTime readTime;

    public void markAsRead() {
        this.readStatus = true;
        this.readTime = LocalDateTime.now();
    }

    @Transient
    public String getContent() {
        return StringUtils.formatContent(this.notifyTemplate.getContent(), this.templateParams);
    }

}
