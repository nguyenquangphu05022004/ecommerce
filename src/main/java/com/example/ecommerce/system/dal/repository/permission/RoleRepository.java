package com.example.ecommerce.system.dal.repository.permission;

import com.example.ecommerce.system.dal.dataobject.permission.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
