package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "conversations")
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conversation extends Base{

    @ManyToMany
    @JoinTable(name = "conversation_user", joinColumns = @JoinColumn(name = "conversation_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "senderToConversation")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @Transient
    public String getConversationName(String username) {
        String conversationName = users.stream()
                .filter(user -> user.getUsername()
                        .compareTo(username) != 0)
                .map(user -> user.getUserContactDetails().getFullName())
                .collect(Collectors.joining(", "));
        return conversationName;
    }
}
