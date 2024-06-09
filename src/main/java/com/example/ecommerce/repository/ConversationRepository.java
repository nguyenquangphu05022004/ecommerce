package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
