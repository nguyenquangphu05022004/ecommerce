package com.example.ecommerce.repository.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.repository.EventRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private final EntityManager entityManager;
    @Override
    public void createEvent(Long entityId) {
        String sql = String.format(
                "create event %s\n" +
                        "ON SCHEDULE AT timestamp('%s') + interval %s hour\n" +
                        "do\n" +
                        "begin\n" +
                        "    update orders\n" +
                        "        set approval = true\n" +
                        "    where id = %s;\n" +
                        "end;",
                "test_" + entityId,
                SystemUtils.getFormatDate(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"),
                SystemUtils.EVENT_TIME_HOUR_EXPIRE,
                entityId);
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
