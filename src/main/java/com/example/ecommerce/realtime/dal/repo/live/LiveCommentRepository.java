package com.example.ecommerce.realtime.dal.repo.live;

import com.example.ecommerce.realtime.dal.dataobject.live.LiveComment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface LiveCommentRepository extends JpaRepository<LiveComment, Long> {

    @Transactional
    @Modifying
    void deleteAllByLiveStreamId(Long liveStreamId);

    List<LiveComment> findAllByLiveStreamId(Long liveStreamId);

}
