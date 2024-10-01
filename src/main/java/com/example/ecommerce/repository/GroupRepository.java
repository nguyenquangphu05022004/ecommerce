package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
