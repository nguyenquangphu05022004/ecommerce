package com.example.ecommerce.converter;

import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.user.UserRequest;
import com.example.ecommerce.domain.singleton.UserTrack;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;


    public User toEntity(UserRequest userRequest) {
        User user = mapper.map(userRequest, User.class);
        user.setRole(Role.USER);
        return user;
    }

    public UserResponseInfo toDto(User user) {
        UserResponseInfo userResponseInfo = mapper.map(user, UserResponseInfo.class);
        return userResponseInfo;
    }


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
