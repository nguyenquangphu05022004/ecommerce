package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.controller.admin.vo.permission.MenuCreateReqVO;
import com.example.ecommerce.system.controller.admin.vo.permission.MenuUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Menu;

import java.util.Arrays;
import java.util.List;

public interface MenuService {

    Menu createMenu(MenuCreateReqVO reqVO);
    Menu updateMenu(MenuUpdateReqVO reqVO);

    void deleteMenuById(Long menuId);
    List<Menu> getList();

    default void deleteAll(Long[] menuIds) {
        Arrays.stream(menuIds).forEach(this::deleteMenuById);
    }

}
