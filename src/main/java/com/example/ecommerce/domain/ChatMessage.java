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

    private Long destinationId;

    @Enumerated(EnumType.STRING)
    private ChatMessageDestination chatMessageDestination;

    @OneToMany(mappedBy = "chatMessage",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageImage> images;
}

