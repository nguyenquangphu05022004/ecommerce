package com.example.ecommerce.domain.model.modelviews.evaluation;

import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.model.modelviews.profile.UserSimpleModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EvaluationDetailsModelView extends EvaluationSimpleModelView {
    private List<EvaluationDetailsModelView> children;

    public EvaluationDetailsModelView(Evaluation evaluation) {
        super(evaluation);
        this.children = getList(evaluation.getEvaluations());
    }

    private List<EvaluationDetailsModelView> getList(List<Evaluation> evaluations) {
        if (CollectionUtils.isEmpty(evaluations)) {
            return Collections.emptyList();
        }
        return evaluations
                .stream()
                .map(e -> new EvaluationDetailsModelView(e))
                .toList();
    }
}
