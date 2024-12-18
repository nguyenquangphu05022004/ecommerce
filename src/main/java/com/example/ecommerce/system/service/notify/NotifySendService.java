package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;

import java.util.Map;

public interface NotifySendService {
    /**
     * Gui thong bao toi user
     * @param userId: Id nguoi dung
     * @param templateId: Mau thong bao
     * @param templateParams: Cac tham so mau
     * @return
     */
    NotifyMessage notifySingleMessage(Long userId, Long templateId, Map<String, Object> templateParams);
    void doNotifyMessage();
}
