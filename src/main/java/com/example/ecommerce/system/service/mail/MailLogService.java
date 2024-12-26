package com.example.ecommerce.system.service.mail;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.mail.vo.log.PageMailLogReqVO;
import com.example.ecommerce.system.dal.dataobject.mail.MailLog;
import com.example.ecommerce.system.dal.dataobject.mail.MailTemplate;
import com.example.ecommerce.system.enums.SendMailStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MailLogService {
    MailLog createMailLog(String fromMail, String toMail, String  content, String title);

    MailLog getMailLogById(Long mailLogId);

    List<MailLog> getListMailLog(Long userId);

    PageResult<MailLog> getPageMailLog(PageMailLogReqVO req);

    /**
     * Get page mail log by createdBy(userId)
     * @return
     */
    PageResult<MailLog> getPageMailLogByUserId(Long userId,PageMailLogReqVO req);

    void deleteMailLog(Long id);


    default void deleteMailLog(Collection<Long> ids) {
        if(!CollUtils.isEmpty(ids)) {
            ids.forEach(this::deleteMailLog);
        }
    }
}
