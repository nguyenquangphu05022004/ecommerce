package com.example.ecommerce.domain.dto.chat;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    private Long id;
    private String conversationName;
    private String image;
    private Boolean active;
}
