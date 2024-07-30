package com.example.ecommerce.domain.entities.auth;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Entity
@SuperBuilder(toBuilder = true)
public class Address extends BaseEntity {
    private String address;
    private String province;
    private String district;
    private String ward;
}
