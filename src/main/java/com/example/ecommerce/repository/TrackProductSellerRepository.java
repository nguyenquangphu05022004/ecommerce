package com.example.ecommerce.repository;

import com.example.ecommerce.domain.TrackProductSeller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackProductSellerRepository extends
        JpaRepository<TrackProductSeller, Long> {

    Page<TrackProductSeller> findAll(Pageable pageable);
}
