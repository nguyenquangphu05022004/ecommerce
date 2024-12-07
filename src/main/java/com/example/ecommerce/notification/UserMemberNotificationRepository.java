package com.example.ecommerce.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMemberNotificationRepository extends JpaRepository<UserMemberNotification, Long> {
    Optional<UserMemberNotification> findByUserMemberId(Long userMemberId);
}
