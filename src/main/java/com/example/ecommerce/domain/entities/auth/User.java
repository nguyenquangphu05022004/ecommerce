package com.example.ecommerce.domain.entities.auth;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.Notification;
import com.example.ecommerce.domain.entities.chat.Conversation;
import com.example.ecommerce.domain.entities.file.FileEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class User extends BaseEntity implements UserDetails {

    private String fullName;

    private String username; //email

    private String password;

    private UserType userType;
    private Long userTypeId;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private UserImage userImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Evaluation> evaluations = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Conversation> conversations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private Set<Vendor> vendors;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @Entity
    @Table(name = "user_images")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserImage extends FileEntity {
        @OneToOne
        @JoinColumn(name = "user_id")
        private User user;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User) {
            User u = (User) obj;
            if(u.getId() == this.getId() || this.getUsername().equals(u.getUsername())) return true;
        }
        return false;
    }
}

