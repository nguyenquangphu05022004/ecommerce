package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "verify")
@Data
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Verify extends Base{
    @Column(columnDefinition = "varchar(12)",unique = true)
    private String code;

    private boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
