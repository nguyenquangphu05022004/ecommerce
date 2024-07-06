package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories_images")
public class CategoryImage extends FileEntity{
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
