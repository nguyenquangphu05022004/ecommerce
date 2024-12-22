package com.example.ecommerce.realtime.dal.repo.live;

import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveProductRepository extends JpaRepository<LiveProduct, Long> {
    List<LiveProduct> findAllByLiveStreamId(Long liveStreamId);
}
