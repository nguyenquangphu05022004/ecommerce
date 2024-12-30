package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.realtime.controller.admin.chat.vo.MessageTemplateCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.MessageTemplate;

import java.util.List;

public interface MessageTemplateService {
    MessageTemplate createMessageTemplate(MessageTemplateCreateReqVO req);
    List<MessageTemplate> getListMessageTemplate();
    MessageTemplate updateMessageTemplate(MessageTemplateCreateReqVO req);
}
