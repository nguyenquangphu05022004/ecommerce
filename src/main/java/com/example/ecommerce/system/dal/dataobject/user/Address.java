package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Table(name = "sys_address")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {
    private Boolean defaultAddress;

    private String city;
    private String district;
    private String commune;

    private String detailAddress;

    private String fullName;
    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserMember user;

    @Transient
    @JsonIgnore
    public String fullAddress() {
      return detailAddress + ", " + commune + ", " + district + ", " + city;
    }
}
