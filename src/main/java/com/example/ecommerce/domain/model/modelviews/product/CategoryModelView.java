package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryModelView extends BaseEntity implements ImageMapper {
    private String name;
    private String slug;
    private String urlImage;
    public CategoryModelView(Category category) {
        this.name = category.getName();
        this.slug = category.getSlug();
        setId(category.getId());
        try {
            this.urlImage = getImageUrl(category.getImages().get(category.getImages().size() - 1));
        } catch (Exception e) {
            this.urlImage = null;
        }
    }
}
