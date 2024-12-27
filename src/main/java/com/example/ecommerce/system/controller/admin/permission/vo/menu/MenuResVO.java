package com.example.ecommerce.system.controller.admin.permission.vo.menu;

import com.example.ecommerce.system.dal.dataobject.permission.Menu;
import lombok.Data;

@Data
public class MenuResVO extends MenuCreateReqVO{

    private Long id;

    public MenuResVO(Menu menu) {
        this.id = menu.getId();
        this.setMenuType(menu.getMenuType());
        setName(menu.getName());
    }
}
