package com.example.ecommerce.converter;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.UserTrack;

import java.util.stream.Collectors;
public class UserMapper {
    public static UserInboxResponse maptoResponse(User user) {
        UserInboxResponse userResponse = UserInboxResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getUserContactDetails() == null ? "CHUA CO TEN" : user.getUserContactDetails().getFullName())
                .conversationResponses(
                        user.getConversations()
                                .stream()
                                .map(conversation -> {
                                    return ConversationMapper
                                            .mapperToConversationResponse(
                                                    conversation, user.getUsername()
                                            );
                                })
                                .collect(Collectors.toList())
                )
                .active(UserTrack.getInstance().getMap().get(user.getUsername()))
                .build();
        return userResponse;
    }
}
