package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "languages")
@Data
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Language extends Base{

    @OneToOne(mappedBy = "language")
    private Product product;
    private String nameVn;
    private String nameEn;
    public Language(String nameVn, String nameEn) {
        this.nameVn = nameVn; this.nameEn = nameEn;
    }
}
