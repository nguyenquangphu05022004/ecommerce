package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "chat_messages")
public class ChatMessage extends BaseEntity {
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @OneToMany(mappedBy = "chatMessage",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageImage> images;
}

@Entity
@Table(name = "chat_message_images")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
class ChatMessageImage extends FileEntity{
    @ManyToOne
    @JoinColumn(name = "chat_message_id")
    private ChatMessage chatMessage;
}
