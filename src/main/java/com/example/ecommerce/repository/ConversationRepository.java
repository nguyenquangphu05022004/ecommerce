package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.chat.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
