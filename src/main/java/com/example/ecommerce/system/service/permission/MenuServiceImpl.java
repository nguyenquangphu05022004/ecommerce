package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuCreateReqVO;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Menu;
import com.example.ecommerce.system.dal.repository.permission.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.MENU_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;
    @Override
    public Menu createMenu(MenuCreateReqVO reqVO) {
        Menu menu = Menu.builder().menuType(reqVO.getMenuType())
                .name(reqVO.getName())
                .build();
        this.menuRepository.save(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(MenuUpdateReqVO reqVO) {
        Menu menu = getMenuById(reqVO.getId()).toBuilder()
                .name(reqVO.getName())
                .menuType(reqVO.getMenuType())
                .build();
        this.menuRepository.save(menu);
        return menu;
    }

    @Override
    public void deleteMenuById(Long menuId) {

    }

    @Override
    public List<Menu> getList() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getMenuById(Long id) {
        return this.menuRepository.findById(id)
                .orElseThrow(() -> exception(MENU_NOT_FOUND));
    }

    @Override
    public PageResult<Menu> getPageMenu(PageParam pageParam) {
        return null;
    }
}
