package com.example.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "languages")
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Language extends Base{

    private String nameVn;
    private String nameEn;
    public Language(String nameVn, String nameEn) {
        this.nameVn = nameVn; this.nameEn = nameEn;
    }
}
