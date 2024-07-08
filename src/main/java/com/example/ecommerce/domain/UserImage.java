package com.example.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "user_images")
@NoArgsConstructor
@AllArgsConstructor
public class UserImage extends FileEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
