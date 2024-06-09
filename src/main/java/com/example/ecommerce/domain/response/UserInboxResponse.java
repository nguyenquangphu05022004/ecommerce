package com.example.ecommerce.domain.response;

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
    private String avatar;
    private List<ConversationResponse> conversationResponses = new ArrayList<>();

}
