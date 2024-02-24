package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Data
public class Category extends Base{

    @Column(nullable = false, length = 100)
    private String name;
}
