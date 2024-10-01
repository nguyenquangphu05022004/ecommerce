package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value ="select m.*\n" +
            "from msg_messages m\n" +
            "where if('USER' = :toEntityType, (m.from_user_id = :fromId and m.entity_id = :destinationId) or\n" +
            "                                 (m.from_user_id = :destinationId and m.entity_id = :fromId),\n" +
            "         m.entity_id = :destinationId\n" +
            "             and :fromId in (select c.user_id from conversation c where c.group_id = :destinationId))\n",
    nativeQuery = true)
    Page<Message> getMessageDetails(@Param("fromId") Long fromUserId,
                                    @Param("destinationId") Long toEntityId,
                                    @Param("toEntityType") EntityType.Type entityType,
                                    Pageable pageable);

    @Query(value = "select m.*\n" +
            "from msg_messages m\n" +
            "where if(m.entity_type = 'USER',\n" +
            "         m.id in (select max(id)\n" +
            "                  from msg_messages\n" +
            "                  where entity_type = 'USER'\n" +
            "                    and (from_user_id = :fromId or entity_id = :fromId)\n" +
            "                  group by from_user_id + entity_id),\n" +
            "         m.id in (select max(me.id)\n" +
            "                  from msg_messages me\n" +
            "                           inner join msg_groups mg\n" +
            "                                      on me.entity_id = mg.id\n" +
            "                           inner join conversation c\n" +
            "                                      on mg.id = c.group_id\n" +
            "                           inner join users au\n" +
            "                                      on c.user_id = au.id\n" +
            "                  where me.entity_type = 'GROUP'\n" +
            "                    and au.id = :fromId\n" +
            "                  group by me.entity_id))\n" +
            "order by m.created_date\n", nativeQuery = true)
    Page<Message> getMessageGallery(@Param("fromId") Long fromId, Pageable pageable);
}
