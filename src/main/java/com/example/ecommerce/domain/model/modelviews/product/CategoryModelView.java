package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryModelView extends BaseEntity {
    private String name;
    private String slug;

    public CategoryModelView(Category category) {
        this.name = category.getName();
        this.slug = category.getSlug();
        setId(category.getId());
    }
}
