package com.example.ecommerce.domain.entities;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.FileEntity;
import com.example.ecommerce.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "msg_messages")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Message extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    private EntityType toEntityType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> images;

    private boolean watched;


}
