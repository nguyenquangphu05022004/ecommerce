package com.example.ecommerce.file;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "file_storage")
@Entity
@NoArgsConstructor
public class FileEntity extends BaseEntity {
    private String originalName;

    @Column(unique = true)
    private String path;

    @Enumerated(EnumType.STRING)
    private Representation representation;

    public FileEntity(String originalName, String path, Representation representation) {
        this.originalName = originalName;
        this.path = path;
        this.representation = representation;
    }
}
