package com.example.ecommerce.system.dal.dataobject.permission;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Table(name = "sys_permission_menu")
@Entity
@Data
@SuperBuilder(toBuilder = true)
public class Menu extends BaseEntity {

    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    public static enum MenuType {
        BUTTON, DIR
    }

}
