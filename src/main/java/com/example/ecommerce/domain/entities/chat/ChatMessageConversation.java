package com.example.ecommerce.domain.entities.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "chat_message_groups")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageConversation extends ChatMessageEntity{
    @ManyToOne
    @JoinColumn(name = "to_conversation_id")
    private Conversation toConversation;
}
