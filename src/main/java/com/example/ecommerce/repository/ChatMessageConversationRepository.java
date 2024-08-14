package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.chat.ChatMessageConversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageConversationRepository extends JpaRepository<ChatMessageConversation, Long> {
    Page<ChatMessageConversation> findAllByCreatedByAndToConversationId(
            String createdBy,
            Long toConversationId,
            Pageable pageable
    );
}
