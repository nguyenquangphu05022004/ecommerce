package com.example.ecommerce.system.service.notify;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;
import com.example.ecommerce.system.dal.repository.notify.NotifyMessageRepository;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.NOTIFY_MESSAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NotifyMessageServiceImp implements NotifyMessageService{

    private final UserMemberService userMemberService;
    private final NotifyMessageRepository notifyMessageRepository;

    @Override
    public NotifyMessage createNotifyMessage(Long userId,
                                    NotifyTemplate notifyTemplate,
                                    Map<String, Object> templateParams) {
        NotifyMessage notifyMessage = NotifyMessage.builder()
                .userMember(userMemberService.getUserMemberById(userId))
                .notifyTemplate(notifyTemplate).templateParams(templateParams)
                .readStatus(false).build();
        this.notifyMessageRepository.save(notifyMessage);
        return notifyMessage;
    }

    @Override
    public List<NotifyMessage> getUnreadNotifyMessageList(Long userId) {
        return this.notifyMessageRepository.findAllByUserMemberIdAndReadStatus(userId, false);
    }

    @Override
    public PageResult<NotifyMessage> getNotifyMessagePageByUserId(Long userId, PageParam pageParam) {
        Page<NotifyMessage> pageNotifyMessage =
                this.notifyMessageRepository.findAllByUserMemberId(userId, pageParam.buildPageRequest());
        return new PageResult<>(pageNotifyMessage);
    }



    @Override
    public NotifyMessage getNotifyMessageById(Long notifyMessageId) {
        NotifyMessage notifyMessage = this.notifyMessageRepository.findById(notifyMessageId)
                .orElseThrow(() -> exception(NOTIFY_MESSAGE_NOT_FOUND));
        if(!notifyMessage.getReadStatus()) {
            notifyMessage.markAsRead();
            this.notifyMessageRepository.save(notifyMessage);
        }
        return notifyMessage;
    }

    @Override
    public Long getUnreadNotifyMessageCount(Long userId) {
        return (long) getUnreadNotifyMessageList(userId).size();
    }



    @Override
    public void updateReadAllNotifyMessage(Long userId) {
        List<NotifyMessage> notifyMessages = this.notifyMessageRepository.findAllByUserMemberId(userId);
        if(!CollUtils.isEmpty(notifyMessages)) {
            notifyMessages.forEach(notify -> {
                notify.markAsRead();
            });
            this.notifyMessageRepository.saveAll(notifyMessages);
        }
    }

    @Override
    public void deleteNotifyMessage(Long notifyMessageId, Long userId) {
        this.notifyMessageRepository.deleteByIdAndUserMemberId(notifyMessageId, userId);
    }
}
