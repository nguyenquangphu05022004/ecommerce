package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.chat.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Page<ChatMessage> findAllByUserUsernameAndDestinationIdAndChatMessageDestination(
            String username,
            Long destinationId,
            ChatMessage.ChatMessageDestination chatMessageDestination,
            Pageable pageable
    );
}
