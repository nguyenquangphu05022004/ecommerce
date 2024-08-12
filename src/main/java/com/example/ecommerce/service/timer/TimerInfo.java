package com.example.ecommerce.service.timer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TimerInfo {
    private int fireCount;
    private long repeatIntervalMinis;
    private long offsetMinis;
    private boolean repeatForever;
    private String callback;
}
