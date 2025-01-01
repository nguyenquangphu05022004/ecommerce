package com.example.ecommerce.system.dal.repository.notify;

import com.example.ecommerce.system.dal.dataobject.notify.NotifyTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotifyTemplateRepository extends JpaRepository<NotifyTemplate, Long> {
    Optional<NotifyTemplate> findByNameIgnoreCase(String name);
}
