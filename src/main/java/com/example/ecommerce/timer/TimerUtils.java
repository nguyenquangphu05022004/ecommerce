package com.example.ecommerce.timer;

import org.quartz.*;

import java.util.Date;

public class TimerUtils {

    protected static JobDetail buildJobDetail(Class classJob, TimerInfo timerInfo) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(classJob.getSimpleName(), timerInfo);
        return  JobBuilder
                .newJob(classJob)
                .setJobData(jobDataMap)
                .withIdentity(classJob.getName())
                .build();
    }
    protected static Trigger buildTrigger(Class classJob, TimerInfo timerInfo) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(timerInfo.getRepeatIntervalMinis());
        if(timerInfo.isRepeatForever()) {
            builder= builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(timerInfo.getFireCount());
        }
        return TriggerBuilder
                .newTrigger()
                .withSchedule(builder)
                .withIdentity(classJob.getSimpleName())
                .startAt(new Date(System.currentTimeMillis() + timerInfo.getOffsetMinis()))
                .build();
    }
    public static TimerInfo extractTimerInfo(Class classJob, JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        TimerInfo timerInfo = (TimerInfo) jobDataMap.get(classJob.getSimpleName());
        return timerInfo;
    }

}
