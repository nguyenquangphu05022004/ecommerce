package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.frame.common.exception.ExceptionMessage;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.system.controller.admin.notify.vo.template.NotifyTemplateCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;
import com.example.ecommerce.system.dal.repository.notify.NotifyTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.NOTIFY_TEMPLATE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NotifyTemplateServiceImpl implements NotifyTemplateService{
    private final Pattern PATTERN = Pattern.compile( "\\{([^}]+)\\}");
    private final NotifyTemplateRepository notifyTemplateRepository;
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public NotifyTemplate createNotifyTemplate(NotifyTemplateCreateReqVO reqVO) {
        NotifyTemplate notifyTemplate = NotifyTemplate.builder().name(reqVO.getName())
                .params(StringUtils.extractStr(reqVO.getContent(), PATTERN))
                .content(reqVO.getContent()).build();
        this.notifyTemplateRepository.save(notifyTemplate);
        return notifyTemplate;
    }

    @Override
    public NotifyTemplate updateNotifyTemplate(NotifyTemplateCreateReqVO reqVO) {
        NotifyTemplate template = getNotifyTemplateById(reqVO.getId()).toBuilder()
                .content(reqVO.getContent())
                .name(reqVO.getName())
                .params(reqVO.getParams())
                .build();
        this.notifyTemplateRepository.save(template);
        return template;
    }

    @Override
    public List<NotifyTemplate> getListNotifyTemplate() {
        return this.notifyTemplateRepository.findAll();
    }

    @Override
    public NotifyTemplate getNotifyTemplateById(@ExceptionMessage(message = "Not found template with id") Long id) {
        return this.notifyTemplateRepository.findById(id)
                .orElseThrow(() -> exception(NOTIFY_TEMPLATE_NOT_FOUND));
    }

    @Override
    public NotifyTemplate getNotifyTemplateByName(@ExceptionMessage(message = "Not found template name") String templateName) {
        return this.notifyTemplateRepository.findByNameIgnoreCase(templateName)
                .orElseThrow(() -> exception(NOTIFY_TEMPLATE_NOT_FOUND, templateName));
    }

}
