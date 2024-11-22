package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.dal.dataobject.permission.MenuRole;
import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.string.StringUtils.compareIgnoreCase;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService{

    private final RoleService roleService;

    @Override
    public void assignRolesForUser(Long[] roleIds, Long userId) {

    }

    @Override
    public void assignMenusForRole(Long[] menuIds, Long roleId) {

    }

    @Override
    public void removeRoleFromUser(Long[] roleIds, Long userId) {

    }

    @Override
    public void removeMenuFromRole(Long[] menuIds, Long roleId) {

    }

    @Override
    public Set<Role> getListByUserId(Long userId) {
        return null;
    }

    @Override
    public Set<MenuRole> getListMenuRole() {
        return null;
    }

    @Override
    public boolean hasPermission(String permission) {
        /**
         * Role_name:Menu_name
         */
        String words[] = permission.split(":");

        Long userId = null; //SecurityUtils.getLoginUserId();
        Set<Role> roles = this.getListByUserId(userId);
        /**
         * User chua dang nhap
         */
        if(CollectionUtils.isEmpty(roles)) {
            return false;
        }

        /**
         * Check lieu rang user la super admin
         */
        boolean check = roles.stream().anyMatch(s -> s.getRoleType() == RoleType.SUPER_ADMIN);
        if(check) return true;
        /**
         * Kiem tra xem roles cua user co chua words[0](role)
         */
        check = roles
                .stream()
                .anyMatch(s -> compareIgnoreCase(words[0], s.getName()));
        if(!check) return false;

        List<Role> menuRoleIds = convertList(this.getListMenuRole(), s -> s.getRole());
        /**
         * Giao nhau
         */
       return roles.retainAll(menuRoleIds);
    }


}
