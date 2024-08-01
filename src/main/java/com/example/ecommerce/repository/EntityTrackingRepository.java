package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.EntityTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityTrackingRepository extends JpaRepository<EntityTracking, Long> {

}
