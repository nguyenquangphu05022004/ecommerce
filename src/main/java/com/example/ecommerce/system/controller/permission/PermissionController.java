package com.example.ecommerce.system.controller.permission;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.system.service.permission.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/api/permissions")
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
    @PostMapping("/assign/roles/user")
    @PreAuthorize("@ss.hasPermission('permission:assign-roles-user')")
    public CommonResult<?> assignRolesForUser() {
        this.permissionService.assignRolesForUser(null, null);
        return null;
    }

    @Operation(summary = "Gan menu cho role")
    @PostMapping("/assign/menus/role")
    @PreAuthorize("@ss.hasPermission('permission:assign-menus-role')")
    public CommonResult<?> assignMenusForRole() {
//        this.permissionService.assignMenusForRole();
        return null;
    }

}
