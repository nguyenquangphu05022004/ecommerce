package com.example.ecommerce.realtime.dal.repo.chat;

import com.example.ecommerce.realtime.dal.dataobject.chat.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
}
