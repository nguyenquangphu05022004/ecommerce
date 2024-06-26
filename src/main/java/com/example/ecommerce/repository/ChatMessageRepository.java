package com.example.ecommerce.repository;

import com.example.ecommerce.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllBySenderToConversationId(Long conversationId);
}
