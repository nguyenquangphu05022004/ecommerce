package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sys_user_member")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserMember extends BaseEntity {
    private String username;
    private String password;
    private Boolean online;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Boolean locked;



    public static enum Sex {
        FEMALE,
        MALE
    }

    @JsonIgnore
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
