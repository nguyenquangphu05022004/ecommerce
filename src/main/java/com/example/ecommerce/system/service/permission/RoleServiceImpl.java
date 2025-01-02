package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.controller.admin.permission.vo.role.RoleCreateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.dal.redis.dao.RedisPermissionDao;
import com.example.ecommerce.system.dal.repository.permission.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.ROLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final RedisPermissionDao redisPermissionDao;
    @Override
    public Role createRole(RoleCreateReqVO reqVO) {
        Role role = Role.builder().name(reqVO.getName())
                .userType(reqVO.getUserType())
                .build();
        this.roleRepository.save(role);
        return role;
    }

    @Override
    public Role updateRole(RoleCreateReqVO reqVO) {
        Role role = getRoleById(reqVO.getId()).toBuilder().userType(reqVO.getUserType())
                .name(reqVO.getName()).build();
        this.roleRepository.save(role);
        return role;
    }

    @Override
    public void deleteRoleById(Long roleId) {

    }

    @Override
    public List<Role> getList() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> exception(ROLE_NOT_FOUND));
    }


    @Override
    public boolean hasRole(String role) {
        return false;
    }

    @Override
    public boolean hasSuperAdmin(String role) {
        return false;
    }
}
