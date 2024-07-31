package com.example.ecommerce.domain.entities.chat;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Long fromUserId;

    private Long toDestinationId;
    @Enumerated(EnumType.STRING)
    private ChatMessageType chatMessageType;

    @OneToMany(mappedBy = "chatMessage",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageImage> images;
}

