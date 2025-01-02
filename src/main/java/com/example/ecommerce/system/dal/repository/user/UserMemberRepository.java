package com.example.ecommerce.system.dal.repository.user;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserMemberRepository extends JpaRepository<UserMember, Long>, JpaSpecificationExecutor<UserMember> {
    Optional<UserMember> findByUsernameIgnoreCase(String username);
}
