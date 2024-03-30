package com.example.ecommerce.repository;

import com.example.ecommerce.entity.TrackProductSeller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackProductSellerRepository extends
        JpaRepository<TrackProductSeller, Long> {

    Page<TrackProductSeller> findAll(Pageable pageable);
}
