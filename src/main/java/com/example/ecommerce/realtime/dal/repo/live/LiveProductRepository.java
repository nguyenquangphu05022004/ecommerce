package com.example.ecommerce.realtime.dal.repo.live;

import com.example.ecommerce.realtime.dal.dataobject.live.LiveProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface LiveProductRepository extends JpaRepository<LiveProduct, Long> {
    List<LiveProduct> findAllByLiveStreamId(Long liveStreamId);
    @Transactional
    @Modifying
    void deleteAllByLiveStreamId(Long liveStreamId);
}
