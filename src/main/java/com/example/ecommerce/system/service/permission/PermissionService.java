package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.dal.dataobject.permission.MenuRole;
import com.example.ecommerce.system.dal.dataobject.permission.Role;

import java.util.Set;

public interface PermissionService {


    void assignRolesForUser(Long[] roleIds, Long userId);

    void assignMenusForRole(Long[] menuIds, Long roleId);


    void removeRoleFromUser(Long[] roleIds, Long userId);

    void removeMenuFromRole(Long[] menuIds, Long roleId);

    Set<Role> getListByUserId(Long userId);

    Set<MenuRole> getListMenuRole();

    /**
     * Permission co format la: [name_role:name_menu]
     * @param permission
     * @return
     */

    boolean hasPermission(String permission);

    default boolean hasAnyPermission(String... permissions) {
        for(String permission : permissions) {
            if(hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }

}
