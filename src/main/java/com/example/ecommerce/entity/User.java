package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Getter
@NoArgsConstructor
public class User extends Base{

    /**
     * user:
          	id, username, password,
          	role, customer
     */
    @Column(length = 20)
    private String username;
    @Column(length = 20)
    private String password;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    private String role;
    @OneToOne(mappedBy = "user")
    private Customer customer;
    @OneToOne(mappedBy = "user")
    private Vendor vendor;
    @OneToMany(mappedBy = "user")
    private List<Evaluation> feedBacks = new ArrayList<>();
}
