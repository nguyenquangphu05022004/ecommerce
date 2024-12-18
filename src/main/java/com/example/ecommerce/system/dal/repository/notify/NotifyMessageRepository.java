package com.example.ecommerce.system.dal.repository.notify;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotifyMessageRepository extends JpaRepository<NotifyMessage, Long> {
    List<NotifyMessage> findAllByUserMemberIdAndReadStatus(Long userId, Boolean readStatus);
    Page<NotifyMessage> findAllByUserMemberIdAndNotifyTemplateId(
        Long userMemberId, Long notifyTemplateId, Pageable pageable
    );
    List<NotifyMessage> findAllByUserMemberId(Long userId);
}
