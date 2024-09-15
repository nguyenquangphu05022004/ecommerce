package com.example.ecommerce.domain.entities.chat;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.FileEntity;
import com.example.ecommerce.domain.entities.auth.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "msg_groups")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Group extends BaseEntity {
    private String name;




    @ManyToMany
    @JoinTable(name = "conversation",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> images;


    public Set<User> getUsers() {
        if(users == null) {
            users = new HashSet<>();
        }
        return users;
    }
}
