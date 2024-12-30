package com.example.ecommerce.realtime.dal.dataobject.chat;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.converter.JsonListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class allow user, attach product, order, ...vv, when want to
 * ask seller about them
 */
@Table(name = "realtime_chat_message_template")
@Entity
@Getter
@Setter
public class MessageTemplate extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String content;
    @Convert(converter = JsonListConverter.class)
    private List<String> params;
}
