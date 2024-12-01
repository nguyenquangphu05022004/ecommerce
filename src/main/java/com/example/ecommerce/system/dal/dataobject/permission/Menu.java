package com.example.ecommerce.system.dal.dataobject.permission;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "sys_permission_menu")
@Entity
@Data
public class Menu extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    public static enum MenuType {
        BUTTON, DIR
    }

}
