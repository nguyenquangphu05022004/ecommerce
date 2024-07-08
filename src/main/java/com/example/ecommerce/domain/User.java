package com.example.ecommerce.domain;

import com.example.ecommerce.common.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class User extends BaseEntity {
    @Column(length = 30, unique = true)
    private String username;
    @Column(length = 30)
    private String password;

    @Column(length = 50, unique = true)
    private String email;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private UserImage userImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private UserContactDetails userContactDetails;

    @OneToOne(mappedBy = "user")
    private Vendor vendor;

    @OneToMany(mappedBy = "user")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Verify verify;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Conversation> conversations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username);
    }
}

