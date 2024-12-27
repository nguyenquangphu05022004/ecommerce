package com.example.ecommerce.system.controller.admin.permission;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuCreateReqVO;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuResVO;
import com.example.ecommerce.system.controller.admin.permission.vo.menu.MenuUpdateReqVO;
import com.example.ecommerce.system.service.permission.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/permission/menus")
@Tag(name = "Menu")
public class MenuController {
    private final MenuService menuService;

    @Operation(summary = "Tao menu")
    @PreAuthorize("@ss.hasPermission('system-permission-menu:update')")
    @PostMapping
    public CommonResult<MenuResVO> createMenu(@RequestBody MenuCreateReqVO req) {
        return CommonResult.success(this.menuService.createMenu(req), MenuResVO::new);
    }

    @Operation(summary = "Tao menu")
    @PreAuthorize("@ss.hasPermission('system-permission-menu:update')")
    @PutMapping
    public CommonResult<MenuResVO> updateMenu(@RequestBody MenuUpdateReqVO req) {
        return CommonResult.success(this.menuService.updateMenu(req), MenuResVO::new);
    }

    @GetMapping
    @PreAuthorize("@ss.hasPermission('system-permission-menu:get')")
    @Operation(summary = "Lay toan bo menu, phan trang")
    public CommonResult<PageResult<MenuResVO>> getPageMenu(@RequestBody PageParam pageParam) {
        return CommonResult.success(this.menuService.getPageMenu(pageParam), MenuResVO::new);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPermission('system-permission-menu:delete')")
    @Operation(summary = "Xoa menu")
    public CommonResult<Boolean> deleteMenu(@PathVariable("id") Long menuId) {
        this.menuService.deleteMenuById(menuId);
        return CommonResult.success(true);
    }
}
