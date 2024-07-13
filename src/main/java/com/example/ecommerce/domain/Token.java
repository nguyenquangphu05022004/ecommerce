package com.example.ecommerce.domain;

import com.example.ecommerce.common.enums.TokenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "token")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Token extends BaseEntity {
    @Column(unique = true)
    private String value;

    private boolean expired;
    private boolean revoked;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
