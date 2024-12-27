package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
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
     *
     * @param userId
     * @param pageParam
     * @return
     */
    PageResult<NotifyMessage> getNotifyMessagePageByUserId(Long userId, PageParam pageParam);

    NotifyMessage getNotifyMessageByIdAndUserId(Long notifyMessageId, Long userId);

    /**
     * Dem so luong thong bao chua doc
     * @param userId
     * @return
     */
    Long getUnreadNotifyMessageCount(Long userId);


    default void updateReadNotifyMessage(Collection<Long> ids, Long userId) {
        if(!CollUtils.isEmpty(ids)) {
            ids.forEach(id -> getNotifyMessageByIdAndUserId(id, userId));
        }
    }

    /**
     * Danh dau tat ca thong bao nguoi dung la da doc
     * @param userId
     */
    void updateReadAllNotifyMessage(Long userId);

    void deleteNotifyMessage(Long notifyMessageId, Long userId);
}
