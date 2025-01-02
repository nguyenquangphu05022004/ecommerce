package com.example.ecommerce.system.dal.repository.permission;

import com.example.ecommerce.system.dal.dataobject.permission.MenuRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MenuRoleRepository extends JpaRepository<MenuRole, Long> {
    @Modifying
    @Transactional
    void deleteAllByMenuIdAndRoleId(Long menuId, Long roleId);

    List<MenuRole> findAllByMenuName(String menuName);
}
