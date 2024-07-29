package com.example.ecommerce.domain.entities.file;

import com.example.ecommerce.domain.entities.product.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "categories_images")
public class CategoryImage extends FileEntity {
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
