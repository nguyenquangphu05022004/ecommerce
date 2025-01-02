package com.example.ecommerce.system.dal.dataobject.permission;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.enums.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@Table(name = "sys_permission_role")
@Entity
@SuperBuilder(toBuilder = true)
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
