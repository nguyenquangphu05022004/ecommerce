package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.frame.common.exception.ExceptionMessage;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.frame.operatelog.enums.OperationType;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.NOTIFY_TEMPLATE_PARAMS_MISSING_KEY;

@Service
@RequiredArgsConstructor
public class NotifySendServiceImpl implements NotifySendService{

    private final NotifyTemplateService notifyTemplateService;
    private final NotifyMessageService notifyMessageService;
    @Override
    @OperationLog(operationType = OperationType.CREATE, logArgs = true)
    public NotifyMessage notifySingleMessage(Long userId, Long templateId, Map<String, Object> templateParams) {
        NotifyTemplate notifyTemplate = this.notifyTemplateService.getNotifyTemplateById(templateId);
        validTemplateParams(notifyTemplate, templateParams);
        return notifyMessageService.createNotifyMessage(userId, notifyTemplate, templateParams);
    }

    @Override
    @OperationLog(operationType = OperationType.CREATE, logArgs = true)
    public void notifySingleMessage(Long userId, String templateName,
                                    Map<String, Object> templateParams) {
        NotifyTemplate notifyTemplate = this.notifyTemplateService.getNotifyTemplateByName(templateName);
        validTemplateParams(notifyTemplate, templateParams);
        notifyMessageService.createNotifyMessage(userId, notifyTemplate, templateParams);
    }


    private void validTemplateParams(NotifyTemplate notifyTemplate, Map<String, Object> templateParams) {
        List<String> params = notifyTemplate.getParams();
        params.forEach(param -> {
            if(!templateParams.containsKey(param)) {
                @ExceptionMessage(message = "Not found key") String key = param;
                throw exception(
                        NOTIFY_TEMPLATE_PARAMS_MISSING_KEY,
                        key
                );
            }
        });
    }


    @Override
    public void doNotifyMessage() {
        throw new UnsupportedOperationException("not support");
    }
}
