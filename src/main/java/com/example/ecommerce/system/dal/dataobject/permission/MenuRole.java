package com.example.ecommerce.system.dal.dataobject.permission;


import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class MenuRole extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
