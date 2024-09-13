package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.chat.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m")
    Page<Message> getMessageDetails(Long fromUserId,
                                    Long toEntityId,
                                    Pageable pageable);

    @Query("select m from Message m")
    Page<Message> getMessageGallery(Long fromUserId, Pageable pageable);
}
