package com.example.ecommerce.service.timer;

import com.example.ecommerce.handler.exception.GeneralException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimerService {
    private final Scheduler scheduler;

    public void schedulerJob(Class classJob, TimerInfo timerInfo) {
        try {
            JobDetail jobDetail = TimerUtils.buildJobDetail(classJob, timerInfo);
            Trigger trigger = TimerUtils.buildTrigger(classJob, timerInfo);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }


    public void getAllJob() {

    }



    @PostConstruct
     void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @PreDestroy
     void destroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new GeneralException(e.getMessage());
        }
    }

}
