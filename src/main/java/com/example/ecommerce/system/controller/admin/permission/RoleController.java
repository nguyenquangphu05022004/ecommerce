package com.example.ecommerce.system.controller.admin.permission;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.system.controller.admin.permission.vo.role.RoleCreateReqVO;
import com.example.ecommerce.system.controller.admin.permission.vo.role.RoleRespVO;
import com.example.ecommerce.system.service.permission.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/system/permission/roles")
@Tag(name = "Role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @Operation(summary = "Tao role")
    @PreAuthorize("@ss.hasPermission('system-permission-role:update')")
    public CommonResult<RoleRespVO> createRole(@RequestBody RoleCreateReqVO req) {
        return CommonResult.success(roleService.createRole(req), RoleRespVO::new);
    }

    @PutMapping
    @Operation(summary = "Cap nhat role")
    @PreAuthorize("@ss.hasPermission('system-permission-role:update')")
    public CommonResult<RoleRespVO> updateRole(@RequestBody RoleCreateReqVO req) {
        return CommonResult.success(roleService.updateRole(req), RoleRespVO::new);
    }

    @GetMapping
    @Operation(summary = "Lay tat ca roles")
    @PreAuthorize("@ss.hasPermission('system-permission-role:get')")
    public CommonResult<List<RoleRespVO>> getListRole() {
        return CommonResult.success(CollUtils.convertList(roleService.getList(), RoleRespVO::new));
    }


}
