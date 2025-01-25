package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.realtime.controller.admin.chat.vo.MessageTemplateCreateReqVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.MessageTemplate;
import com.example.ecommerce.realtime.dal.repo.chat.MessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.MESSAGE_TEMPLATE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl implements MessageTemplateService{
    private final MessageTemplateRepository messageTemplateRepository;
    private final Pattern PATTERN = Pattern.compile( "\\{([^}]+)\\}");
    @Override
    public MessageTemplate createMessageTemplate(MessageTemplateCreateReqVO req) {
        MessageTemplate messageTemplate = new MessageTemplate();
        if(req.getId() != null) {
            messageTemplate = getTemplateById(req.getId());
        }
        messageTemplate.setContent(req.getContent()); messageTemplate.setName(req.getName());
        messageTemplate.setParams(StringUtils.extractStr(req.getContent(), PATTERN));
        messageTemplateRepository.save(messageTemplate);
        return messageTemplate;
    }

    @Override
    public List<MessageTemplate> getListMessageTemplate() {
        return messageTemplateRepository.findAll();
    }

    @Override
    public MessageTemplate getTemplateById(Long templateId) {
        return this.messageTemplateRepository.findById(templateId)
                .orElseThrow(() -> exception(MESSAGE_TEMPLATE_NOT_FOUND));
    }

}
