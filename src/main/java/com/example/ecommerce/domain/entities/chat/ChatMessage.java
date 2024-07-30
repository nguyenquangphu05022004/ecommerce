package com.example.ecommerce.domain.entities.chat;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.file.FileEntity;
import com.example.ecommerce.domain.entities.auth.User;
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

    private Long destinationId;

    @Enumerated(EnumType.STRING)
    private ChatMessageDestination chatMessageDestination;

    @OneToMany(mappedBy = "chatMessage",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageImage> images;
    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Entity
    @Table(name = "chat_message_images")
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder(toBuilder = true)
    @Getter
    @Setter
    public static class ChatMessageImage extends FileEntity {
        @ManyToOne
        @JoinColumn(name = "chat_message_id")
        private ChatMessage chatMessage;
    }

    public static enum ChatMessageDestination {
        USER,
        GROUP
    }


}
