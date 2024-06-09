package com.example.ecommerce.domain.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    private Long id;
    private String conversationName;
    private List<ChatMessageResponse> chatMessageResponses = new ArrayList<>();
}
