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

@MappedSuperclass
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class ChatMessageEntity extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageImage> chatMessageImages;
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;
    private String content;


}
