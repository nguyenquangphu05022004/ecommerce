package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuCreateReqVO;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuUpdateReqVO;
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

    @Override
    public PageResult<Menu> getPageMenu(PageParam pageParam) {
        return null;
    }
}
