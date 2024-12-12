package com.example.ecommerce.realtime.dal.dataobject.live;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.Seller;


public class LiveStream extends BaseEntity {
    private String title;
    private Seller seller;

    private Integer totalView;
    private Integer totalViewLive;

    private Boolean started;
    private Boolean closed;
}
