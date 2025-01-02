package com.example.ecommerce.system.dal.repository.permission;

import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.dal.dataobject.permission.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {

    @Transactional
    @Modifying
    void deleteAllByRoleIdAndUserMemberId(Long roleId, Long userId);

    List<RoleUser> findAllByUserMemberId(Long userId);

}
