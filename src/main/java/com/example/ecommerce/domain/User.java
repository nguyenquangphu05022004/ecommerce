package com.example.ecommerce.domain;

import com.example.ecommerce.controller.FilesStorageController;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@Getter
@NoArgsConstructor
@ToString
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

    @ManyToMany(mappedBy = "users")
    private List<Conversation> conversations = new ArrayList<>();


    @Transient
    @Value("${image.default}")
    private String defaultAvatar;

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
            return this.defaultAvatar;
        }
        return "/files/image/" + this.avatar.getName();
    }
}
