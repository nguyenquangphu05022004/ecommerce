package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "verify")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Verify extends Base{
    @Column(columnDefinition = "varchar(12)",unique = true)
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public boolean isExpired() {
        if(getModifiedDate().plusMinutes(5l).isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }
}
