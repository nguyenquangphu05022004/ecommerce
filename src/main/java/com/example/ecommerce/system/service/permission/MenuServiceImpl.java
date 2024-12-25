package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.controller.admin.vo.permission.MenuCreateReqVO;
import com.example.ecommerce.system.controller.admin.vo.permission.MenuUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Override
    public Menu createMenu(MenuCreateReqVO reqVO) {
        return null;
    }

    @Override
    public Menu updateMenu(MenuUpdateReqVO reqVO) {
        return null;
    }

    @Override
    public void deleteMenuById(Long menuId) {

    }

    @Override
    public List<Menu> getList() {
        return null;
    }
}
