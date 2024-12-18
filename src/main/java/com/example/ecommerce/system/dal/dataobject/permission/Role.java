package com.example.ecommerce.system.dal.dataobject.permission;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "sys_permission_role")
@Entity
public class Role extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
