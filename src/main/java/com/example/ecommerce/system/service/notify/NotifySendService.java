package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface NotifySendService {
    /**
     * Gui thong bao toi user
     * @param userId: Id nguoi dung
     * @param templateId: Mau thong bao
     * @param templateParams: Cac tham so mau
     * @return
     */
    @Async
    NotifyMessage notifySingleMessage(Long userId, Long templateId, Map<String, Object> templateParams);
    @Async
    NotifyMessage notifySingleMessage(Long userId, String templateName, Map<String, Object> templateParams);

    void doNotifyMessage();
}
