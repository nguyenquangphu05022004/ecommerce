package com.example.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedBy
    @Column(columnDefinition = "varchar(20)")
    private String createdBy;
    @LastModifiedBy
    @Column(columnDefinition = "varchar(20)")
    private String modifiedBy;
    @CreatedDate
    @Column(columnDefinition = "datetime2(2)")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(columnDefinition = "datetime2(2)")
    private LocalDateTime modifiedDate;
}
