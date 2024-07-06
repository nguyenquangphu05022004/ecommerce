package com.example.ecommerce.domain;

import com.example.ecommerce.common.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "conversations")
@Setter
@SuperBuilder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conversation extends BaseEntity {

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
                .map(user -> {
                    if(user.getRole() == Role.VENDOR) {
                        return user.getVendor().getShopName();
                    }
                    return user.getUserContactDetails() != null ? user.getUserContactDetails().getFullName() : user.getUsername();
                })
                .collect(Collectors.joining(", "));
        return conversationName;
    }
}
