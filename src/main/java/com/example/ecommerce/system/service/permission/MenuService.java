package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuCreateReqVO;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Menu;

import java.util.Arrays;
import java.util.List;

public interface MenuService {

    Menu createMenu(MenuCreateReqVO reqVO);
    Menu updateMenu(MenuUpdateReqVO reqVO);

    void deleteMenuById(Long menuId);
    List<Menu> getList();
    Menu getMenuById(Long id);
    default void deleteAll(Long[] menuIds) {
        Arrays.stream(menuIds).forEach(this::deleteMenuById);
    }

    PageResult<Menu> getPageMenu(PageParam pageParam);
}
