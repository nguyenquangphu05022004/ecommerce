package com.example.ecommerce.system.dal.repository.permission;

import com.example.ecommerce.system.dal.dataobject.permission.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
}
