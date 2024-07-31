package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.chat.ChatMessageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Page<ChatMessage> findAllByFromUserIdAndToDestinationIdAndChatMessageType(
            Long fromUserId,
            Long toDestinationId,
            ChatMessageType chatMessageType,
            Pageable pageable
    );
}
