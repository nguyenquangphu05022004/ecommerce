package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.chat.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
