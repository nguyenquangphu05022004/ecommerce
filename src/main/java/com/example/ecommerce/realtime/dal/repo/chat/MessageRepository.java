package com.example.ecommerce.realtime.dal.repo.chat;

import com.example.ecommerce.realtime.dal.dataobject.chat.Message;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Transactional
    @Modifying
    @Query(value = "update realtime_chat_message c\n" +
            "set c.read_message = true\n" +
            "where c.to_user_id + c.from_user_id = :fromUserId + :toUserId ", nativeQuery = true)
    void updateStatusFromReadMessages(@Param("fromUserId") Long fromUserId,
                                      @Param("toUserId") Long toUserId);

    @Query(value = "select c.* from realtime_chat_message c\n" +
            "where c.from_user_id + c.to_user_id = :fromUserId + :toUserId\n", nativeQuery = true)
    List<Message> findAllMessageBetweenTwoUsers(@Param("fromUserId") Long fromUserId,
                                                @Param("toUserId") Long toUserId);

    @Query(value = "delete from realtime_chat_message c where (c.from_user_id = :userOne and c.to_user_id = :userTwo) " +
            "or (c.from_user_id = :userTwo and c.to_user_id = :userOne)", nativeQuery = true)
    @Modifying
    void deleteAllMessage(@Param("userOne") Long userOne,@Param("userTwo") Long userTwo);
    /**
     *
     * @param userId:1
     * @return [
     *     [3, 2], //-> with userId: 3 -> user1 has 2 messages unread;
     *     [100, 6] //-> with userId: 100 -> user1 has 100 messages unread;
     * ]
     */
    @Query(value = "select m.from_user_id, count(m.*)\n" +
            "from realtime_chat_message m\n" +
            "where m.to_user_id = :userId and m.read_message = false\n" +
            "group by m.from_user_id", nativeQuery = true)
    List<Object[]> countUnreadMessageFromUserId(@Param("userId") Long userId);


    /**
     * pattern: message_id:from_user_id:to_user_id
     * messages= [1:1:2, 2:2:1, 3:1:2, 4:1:3, 5:2:1]
     * Message with id largest is new latest message of conversation(between two users)
     * @param fromUserId: 1
     * @return: List Message [5:2:1, 4:1:3]
     */
//    List<Message> findLatestMessageOfEachConversationFromUserId(@Param("fromUserId") Long fromUserId);
}
