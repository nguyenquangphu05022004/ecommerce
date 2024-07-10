package com.example.ecommerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sizes")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Size extends BaseEntity {
    @Column(unique = true)
    private String name;
}
