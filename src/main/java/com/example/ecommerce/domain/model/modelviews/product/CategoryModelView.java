package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.service.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryModelView extends BaseEntity implements ImageMapper {
    private String name;
    private String slug;
    private String urlImage;
    private List<CategoryModelView> categoryModelViews;

    public CategoryModelView(Category category) {
        if (category != null) {
            this.name = category.getName();
            this.slug = category.getSlug();
            setId(category.getId());
            if (!CollectionUtils.isEmpty(category.getImages())) {
                this.urlImage = getImageUrl(category.getImages().get(category.getImages().size() - 1));
            }
            if (!CollectionUtils.isEmpty(category.getChildren())) {
                this.categoryModelViews = category.getChildren().stream()
                        .map(s -> new CategoryModelView(s))
                        .collect(Collectors.toList());
            }
        }
    }
}
