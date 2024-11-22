package com.example.ecommerce.system.dal.repository.permission;

import com.example.ecommerce.system.dal.dataobject.permission.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
