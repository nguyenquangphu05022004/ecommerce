package com.example.ecommerce.domain;

import com.example.ecommerce.domain.dto.ENUM.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class User extends Base{
    @Column(length = 20)
    private String username;
    private String password;
    @Column(length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image avatar;

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

    @Transient
    public String defaultImage() {
        if(this.avatar == null) {
            return "https://ssl.gstatic.com/accounts/ui/avatar_2x.png";
        }
        return "/files/image/" + this.avatar.getName();
    }
}
