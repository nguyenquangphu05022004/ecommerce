package com.example.ecommerce.domain.entities.task;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.EntityType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "task_timer")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Timer extends BaseEntity {
    private Integer repeatFireCount;
    private Boolean repeatForever;
    private Long millisecondsInterval;
    private Long milliOffset;

    @Embedded
    private EntityType callback;
}
