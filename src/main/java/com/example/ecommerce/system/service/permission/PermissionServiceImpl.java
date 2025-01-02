package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.dal.dataobject.permission.Menu;
import com.example.ecommerce.system.dal.dataobject.permission.MenuRole;
import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.dal.dataobject.permission.RoleUser;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.redis.dao.RedisPermissionDao;
import com.example.ecommerce.system.dal.repository.permission.MenuRoleRepository;
import com.example.ecommerce.system.dal.repository.permission.RoleUserRepository;
import com.example.ecommerce.system.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.system.dal.redis.PermissionConstant.*;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService{

    private final RoleService roleService;
    private final RoleUserRepository roleUserRepository;
    private final MenuRoleRepository menuRoleRepository;
    private final RedisPermissionDao redisPermissionDao;
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void assignRolesForUser(Collection<Long> roleIds, Long userId) {
        List<RoleUser> roleUsers = convertList(roleIds, roleId -> {
            return RoleUser.builder()
                    .role(Role.builder().id(roleId).build())
                    .userMember(UserMember.builder().id(userId).build())
                    .build();

        });

        this.roleUserRepository.saveAll(roleUsers);

    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void assignMenusForRole(Collection<Long> menuIds, Long roleId) {
        List<MenuRole> menuRoles = convertList(menuIds, menuId -> {
            return MenuRole.builder().menu(Menu.builder().id(menuId).build())
                    .role(Role.builder().id(roleId).build())
                    .build();
        });

        menuRoleRepository.saveAll(menuRoles);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void removeRoleFromUser(Collection<Long> roleIds, Long userId) {
        CollUtils.convertList(roleIds, roleId -> {
           roleUserRepository.deleteAllByRoleIdAndUserMemberId(roleId, userId);
            return null;
        });
    }

    @Override
    public void removeMenuFromRole(Collection<Long> menuIds, Long roleId) {
        CollUtils.convertList(menuIds, menuId -> {
            menuRoleRepository.deleteAllByMenuIdAndRoleId(menuId, roleId);
            return null;
        });
    }

    @Override
    public Set<Role> getListByUserId(Long userId) {
        List<Role> roles = this.redisPermissionDao.getList(USER_LIST_ROLE,userId.toString());
        if(CollUtils.isEmpty(roles)) {
            roles = CollUtils.convertList(this.roleUserRepository.findAllByUserMemberId(userId), RoleUser::getRole);
            redisPermissionDao.setValue(USER_LIST_ROLE, userId.toString(), roles);
        }
        return new HashSet<>(roles);
    }

    @Override
    public Set<MenuRole> getListMenuRole() {
        List<MenuRole> menuRoles = this.redisPermissionDao.getList(MENU_ROLE_LIST);
        if(CollUtils.isEmpty(menuRoles)) {
            menuRoles = this.menuRoleRepository.findAll();
            redisPermissionDao.setValue(MENU_ROLE_LIST, menuRoles);
        }
        return new HashSet<>(menuRoles);
    }

    @Override
    public Set<MenuRole> getListMenuRoleByMenuName(String menuName) {
        List<MenuRole> menuRoles = this.redisPermissionDao.getList(MENU_LIST_ROLE, menuName);
        if(CollUtils.isEmpty(menuRoles)) {
            menuRoles = this.menuRoleRepository.findAllByMenuName(menuName);
            redisPermissionDao.setValue(MENU_LIST_ROLE, menuName, menuRoles);
        }
        return new HashSet<>(menuRoles);
    }

    @Override
    public boolean hasPermission(String permission) {
        /**
         * pattern = {...}:{menu-name}
         */
        String words[] = permission.split(":");

        Long userLogin = SecurityUtils.getLoginUserMemberId();
        Set<Role> roles = this.getListByUserId(userLogin);
        /**
         * User chua dang nhap
         */
        if(CollectionUtils.isEmpty(roles)) {
            return false;
        }

        /**
         * Check lieu rang user la super admin
         */
        boolean check = roles.stream().anyMatch(s -> s.getUserType() == UserType.SUPER_ADMIN);
        if(check) return true;

        Set<Long> userRoleIds = convertSet(roles, role -> role.getId());

        /**
         * Lay toan bo role co menu: word[1]
         */
        Set<Long> roleIds = convertSet(this.getListMenuRoleByMenuName(words[1]), s -> s.getRole().getId());
        /**
         * Giao nhau
         */
       return userRoleIds.retainAll(roleIds);
    }


}
