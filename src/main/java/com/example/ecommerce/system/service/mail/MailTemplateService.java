package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.mail.vo.template.MailTemplateCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;

import java.util.List;

public interface MailTemplateService {
    /**
     * Tao mau email de gui toi customer
     * @param reqVO
     * @return
     */
    MailTemplate createMailTemplate(MailTemplateCreateReqVO reqVO);

    /**
     * Lay danh sach mau thong bao
     * @return
     */
    List<MailTemplate> getListMailTemplate();
    MailTemplate getMailTemplateById(Long id);
    void deleteMailTemplate(Long id);
}
