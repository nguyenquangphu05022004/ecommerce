package com.example.ecommerce.system.controller.admin.permission;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.system.service.permission.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin-api/system/permissions")
@RequiredArgsConstructor
@Tag(name = "Permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Operation(summary = "Kiem tra xem user member co quyen truy cap vao resource cu the hay khong")
    @GetMapping
    public CommonResult<Boolean> hasPermission(@RequestParam("permission") String permission) {
        return success(this.permissionService.hasPermission(permission));
    }


    @Operation(summary = "Gan roles cho user")
    @PostMapping("/assign/roles/user/{id}")
    @PreAuthorize("@ss.hasPermission('system-permission:permission')")
    public CommonResult<Boolean> assignRolesForUser(@PathVariable("id") Long userId, Collection<Long> roleIds) {
        this.permissionService.assignRolesForUser(roleIds, userId);
        return success(true);
    }

    @Operation(summary = "Xoa roles trong user")
    @DeleteMapping("/assign/roles/user/{id}")
    @PreAuthorize("@ss.hasPermission('system-permission:permission')")
    public CommonResult<Boolean> removeRolesForUser(@PathVariable("id") Long userId, Collection<Long> roleIds) {
        this.permissionService.removeRoleFromUser(roleIds, userId);
        return success(true);
    }

    @Operation(summary = "Gan menu cho role")
    @PostMapping("/assign/menus/role/{id}")
    @PreAuthorize("@ss.hasPermission('system-permission:permission')")
    public CommonResult<Boolean> assignMenusForRole(@PathVariable("id") Long roleId, Collection<Long> menuIds) {
        this.permissionService.assignMenusForRole(menuIds, roleId);
        return success(true);
    }

    @Operation(summary = "Xoa menu trong role")
    @DeleteMapping("/assign/menus/role/{id}")
    @PreAuthorize("@ss.hasPermission('system-permission:permission')")
    public CommonResult<Boolean> removeMenusForRole(@PathVariable("id") Long roleId, Collection<Long> menuIds) {
        this.permissionService.removeMenuFromRole(menuIds, roleId);
        return success(true);
    }

}
