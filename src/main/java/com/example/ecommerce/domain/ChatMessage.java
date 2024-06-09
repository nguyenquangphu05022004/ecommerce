package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_messages")
public class ChatMessage extends Base{
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private User userSender;
    @ManyToOne
    @JoinColumn(name = "sender_conversation")
    private Conversation senderToConversation;
}
