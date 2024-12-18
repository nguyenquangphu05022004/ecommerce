package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.system.controller.notify.vo.template.NotifyTemplateCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;

import java.util.List;
import java.util.Map;

public interface NotifyTemplateService {
    NotifyTemplate createNotifyTemplate(NotifyTemplateCreateReqVO reqVO);
    NotifyTemplate updateNotifyTemplate(NotifyTemplateCreateReqVO reqVO);
    List<NotifyTemplate> getListNotifyTemplate();
    NotifyTemplate getNotifyTemplateById(Long id);
}
