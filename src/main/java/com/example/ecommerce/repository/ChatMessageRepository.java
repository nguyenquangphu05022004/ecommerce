package com.example.ecommerce.repository;

import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.domain.ChatMessageDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByUserUsernameAndDestinationIdAndChatMessageDestination(
            String username,
            Long destinationId,
            ChatMessageDestination chatMessageDestination
    );
}
