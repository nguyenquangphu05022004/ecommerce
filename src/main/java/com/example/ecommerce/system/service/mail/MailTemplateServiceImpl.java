package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.system.controller.mail.vo.template.MailTemplateCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import com.example.ecommerce.system.dal.repository.mail.MailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.regex.Pattern;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.MAIL_TEMPLATE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Validated
public class MailTemplateServiceImpl implements MailTemplateService{
    private final Pattern PATTERN = Pattern.compile( "\\{([^}]+)\\}");
    private final MailTemplateRepository mailTemplateRepository;
    @Override
    public MailTemplate createMailTemplate(MailTemplateCreateReqVO reqVO) {
        MailTemplate mailTemplate = MailTemplate.builder()
                .title(reqVO.getTitle()).name(reqVO.getName()).content(reqVO.getContent())
                .params(StringUtils.extractStr(reqVO.getContent(), PATTERN)).build();
        mailTemplateRepository.save(mailTemplate);
        return mailTemplate;
    }

    @Override
    public List<MailTemplate> getListMailTemplate() {
        return this.mailTemplateRepository.findAll();
    }

    @Override
    public MailTemplate getMailTemplateById(Long id) {
        return this.mailTemplateRepository.findById(id)
                .orElseThrow(() -> exception(MAIL_TEMPLATE_NOT_FOUND));
    }

    @Override
    public void deleteMailTemplate(Long id) {

    }
}
