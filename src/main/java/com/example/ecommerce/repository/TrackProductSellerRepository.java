package com.example.ecommerce.repository;

import com.example.ecommerce.entity.TrackProductSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackProductSellerRepository extends
        JpaRepository<TrackProductSeller, Long> {

    @Query(value = "SELECT tr FROM TrackProductSeller tr ORDER BY tr.numberOfProductsSold DESC LIMIT 9")
    List<TrackProductSeller> findAllBySelectTop9ByNumberOfProductsSoldDESQuC();
}
