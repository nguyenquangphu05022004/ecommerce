package com.example.ecommerce.realtime.dal.dataobject.live;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "realtime_live_stream")
public class LiveStream extends BaseEntity {
    private String titleLiveStream;

    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember hostOwner;

    private Integer totalView;
    private LocalDateTime startDate;


    private Boolean isClosed;
}
