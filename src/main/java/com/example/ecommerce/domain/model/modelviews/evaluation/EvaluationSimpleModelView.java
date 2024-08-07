package com.example.ecommerce.domain.model.modelviews.evaluation;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.model.modelviews.profile.UserSimpleModelView;
import com.example.ecommerce.service.mapper.ImageMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EvaluationSimpleModelView extends BaseEntity implements ImageMapper {
    private String content;
    private UserSimpleModelView user;
    private int rating;
    private Long evalParentId;
    private List<String> imageUrls;
    public EvaluationSimpleModelView(Evaluation evaluation) {
        setId(evaluation.getId());
        this.content = evaluation.getContent();
        this.user = new UserSimpleModelView(evaluation.getUser());
        this.rating = evaluation.getRating();
        this.evalParentId = evaluation.getParent() != null ? evaluation.getParent().getId() : null;
        this.imageUrls = getImageUrl(FileEntityType.EVALUATION.name(), evaluation.getImages());
    }
}
