package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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
