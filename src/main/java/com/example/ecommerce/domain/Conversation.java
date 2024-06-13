package com.example.ecommerce.domain;

import com.example.ecommerce.domain.dto.ENUM.Role;
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
                .map(user -> {
                    if(user.getRole() == Role.VENDOR) {
                        return user.getVendor().getShopName();
                    }
                    return user.getUserContactDetails().getFullName();
                })
                .collect(Collectors.joining(", "));
        return conversationName;
    }
    @Transient
    public String getImageOfConversation(String username) {
        String imageUrl = users.stream()
                .filter(user -> user.getUsername()
                        .compareTo(username) != 0)
                .map(user ->  user.defaultImage())
                .collect(Collectors.joining(", "));
        return imageUrl;
    }
}
