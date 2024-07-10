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
    @OneToMany(mappedBy = "conversation")
    private List<ChatMessage> chatMessages = new ArrayList<>();
}
