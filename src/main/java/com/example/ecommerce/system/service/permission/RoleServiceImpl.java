package com.example.ecommerce.system.service.permission;

import com.example.ecommerce.system.controller.vo.permission.RoleCreateReqVO;
import com.example.ecommerce.system.controller.vo.permission.RoleUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.permission.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Override
    public Role createRole(RoleCreateReqVO reqVO) {
        return null;
    }

    @Override
    public Role updateRole(RoleUpdateReqVO reqVO) {
        return null;
    }

    @Override
    public void deleteRoleById(Long roleId) {

    }

    @Override
    public List<Role> getList() {
        return null;
    }

    @Override
    public List<Role> getAllByUserId(Long userId) {
        return null;
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
