package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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
    @Column(columnDefinition = "text")
    private String thumbnail;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Embedded
    private UserContactDetails userContactDetails;
    @OneToOne(mappedBy = "user")
    private Vendor vendor;
    @OneToMany(mappedBy = "user")
    private List<Evaluation> feedBacks = new ArrayList<>();
}
