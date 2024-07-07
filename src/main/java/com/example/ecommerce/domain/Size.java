package com.example.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "sizes")
@Getter
public class Size extends BaseEntity {
    private String name;
}
