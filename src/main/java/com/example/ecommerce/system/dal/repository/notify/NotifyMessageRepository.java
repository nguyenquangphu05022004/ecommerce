package com.example.ecommerce.system.dal.repository.notify;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NotifyMessageRepository extends JpaRepository<NotifyMessage, Long> {
    List<NotifyMessage> findAllByUserMemberIdAndReadStatus(Long userMemberId, Boolean readStatus);
    Page<NotifyMessage> findAllByUserMemberId(
        Long userMemberId, Pageable pageable
    );
    List<NotifyMessage> findAllByUserMemberId(Long userMemberId);

    Optional<NotifyMessage> findAllByUserMemberIdAndId(Long userMemberId, Long id);

    @Transactional
    @Modifying
    void deleteByIdAndUserMemberId(Long id, Long userMemberId);
}
