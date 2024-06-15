package com.example.ecommerce.domain.dto.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class UserInboxResponse {
    private Long id;
    private String username;
    private String fullName;
    private String image;
    private Boolean active;
    private List<ConversationResponse> conversationResponses = new ArrayList<>();
}
