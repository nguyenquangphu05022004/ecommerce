package com.example.ecommerce.realtime.service.chat;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.realtime.controller.app.chat.vo.user.ChatUserRespVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.ChatUser;
import com.example.ecommerce.realtime.dal.repo.chat.ChatUserRepository;
import com.example.ecommerce.realtime.dal.repo.chat.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.realtime.constants.ErrorCodeConstants.CHAT_BETWEEN_USER_IS_NOT_ESTABLISHED;

@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {
    private final MessageRepository messageRepository;
    private final ChatUserRepository chatUserRepository;

    @Override
    public List<ChatUserRespVO> getListChat(Long userId) {
        List<ChatUserRespVO> chatUsers = CollUtils.convertList(chatUserRepository.findAllChatUser(userId), chatUser -> {
            ChatUserRespVO c = new ChatUserRespVO();
            c.setUserId(chatUser.getUserChat().getId());
            c.setUserAvatar(chatUser.getUserChat().getAvatar());
            c.setOnline(chatUser.getUserChat().getOnline());
            c.setUserFullName(chatUser.getUserChat().getFullName());
            c.setId(chatUser.getId()); c.setNumberUnreadMessage(0L);
            return c;
        });
        List<Object[]> objects = messageRepository.countUnreadMessage(userId);

        Map<Long, ChatUserRespVO> chatUserIdMapChatUser = MapUtils.convertToMap(CollUtils.convertList(chatUsers, chatUser -> {
            return new Pair<>(chatUser.getUserId(), chatUser);
        }));

        if(!MapUtils.isEmpty(chatUserIdMapChatUser)) {
            CollUtils.convertList(objects, objs -> {
                Long toUserId = (long)objs[0];
                if(chatUserIdMapChatUser.containsKey(toUserId)) {
                    chatUserIdMapChatUser.get(toUserId).setNumberUnreadMessage((long)objs[1]);
                };
                return null;
            });
        }
        return chatUsers;
    }

    @Override
    @Transactional
    public void deleteChatUser(Long chatUserId) {
        ChatUser chatUser = this.chatUserRepository.findById(chatUserId)
                .orElseThrow(() -> exception(CHAT_BETWEEN_USER_IS_NOT_ESTABLISHED));
        messageRepository.deleteAllMessage(chatUser.getUserOne().getId(), chatUser.getUserTwo().getId());
        this.chatUserRepository.delete(chatUser);
    }

    @Override
    public ChatUser getChatByTwoUser(Long fromUserId, Long toUserId) {
        return this.chatUserRepository.findChatBetweenUsers(fromUserId, toUserId);
    }
}
