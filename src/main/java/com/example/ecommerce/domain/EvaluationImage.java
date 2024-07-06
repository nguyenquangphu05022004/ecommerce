package com.example.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_evaluation_images")
public class EvaluationImage extends FileEntity{
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;
}
