package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.notify.vo.message.NotifyMessagePageReqVO;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface NotifyMessageService {

    /**
     * Tao thong bao
     * @param userId
     * @param notifyTemplate
     * @param templateParams
     * @return
     */
    NotifyMessage createNotifyMessage(Long userId,
                                      NotifyTemplate notifyTemplate,
                                      Map<String, Object> templateParams);

    /**
     * Lay thong bao chua doc cua user
     * @param userId
     * @return
     */
    List<NotifyMessage> getUnreadNotifyMessageList(Long userId);

    /**
     * Phan trang thong bao
     * @param reqVO
     * @return
     */
    PageResult<NotifyMessage> getNotifyMessagePage(NotifyMessagePageReqVO reqVO);
    NotifyMessage getNotifyMessageById(Long id);

    /**
     * Dem so luong thong bao chua doc
     * @param userId
     * @return
     */
    Long getUnreadNotifyMessageCount(Long userId);

    /**
     * Update status thong bao cua nguoi dung
     * @param notifyMessageId
     * @param userId
     */
    void updateReadNotifyMessage(Long notifyMessageId, Long userId);
    default void updateReadNotifyMessage(Collection<Long> ids, Long userId) {
        if(!CollUtils.isEmpty(ids)) {
            ids.forEach(id -> updateReadNotifyMessage(id, userId));
        }
    }

    /**
     * Danh dau tat ca thong bao nguoi dung la da doc
     * @param userId
     */
    void updateReadAllNotifyMessage(Long userId);

}
