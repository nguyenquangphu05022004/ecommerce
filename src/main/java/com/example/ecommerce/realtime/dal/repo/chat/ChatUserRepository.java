package com.example.ecommerce.realtime.dal.repo.chat;

import com.example.ecommerce.realtime.dal.dataobject.chat.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    @Query("select c from ChatUser c where c.userOne.id = :userId or c.userTwo.id = :userId")
    List<ChatUser> findAllChatUser(@Param("userId") Long userId);
}
