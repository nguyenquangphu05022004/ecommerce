package com.example.ecommerce.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder(toBuilder = true)
public abstract class FileEntity extends BaseEntity{
    private String name;
    private String path;
    private String type;
}
