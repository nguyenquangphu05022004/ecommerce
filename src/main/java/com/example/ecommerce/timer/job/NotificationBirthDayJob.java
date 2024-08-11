package com.example.ecommerce.timer.job;

import com.example.ecommerce.timer.TimerInfo;
import com.example.ecommerce.timer.TimerUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;


@Component
public class NotificationBirthDayJob implements Job {
    @Override
    public void execute(JobExecutionContext exe) {
        TimerInfo timerInfo = TimerUtils.extractTimerInfo(NotificationBirthDayJob.class, exe);

    }
}
