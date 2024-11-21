package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.controller.vo.permission.RoleCreateReqVO;
import com.example.ecommerce.system.controller.vo.permission.RoleUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Role;

import java.util.Arrays;
import java.util.List;

public interface RoleService {

    Role createRole(RoleCreateReqVO reqVO);

    Role updateRole(RoleUpdateReqVO reqVO);

    void deleteRoleById(Long roleId);

    List<Role> getList();

    List<Role> getAllByUserId(Long userId);

    boolean hasRole(String role);
    boolean hasSuperAdmin(String role);
    default  boolean hasAnyRole(String[] roles) {
        return Arrays.stream(roles).anyMatch(this::hasRole);
    }

    default void deleteAll(Long[] roleIds) {
        Arrays.stream(roleIds).forEach(this::deleteRoleById);
    }
}
