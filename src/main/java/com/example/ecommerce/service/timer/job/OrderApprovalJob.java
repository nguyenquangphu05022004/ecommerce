package com.example.ecommerce.service.timer.job;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.timer.TimerInfo;
import com.example.ecommerce.service.timer.TimerUtils;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderApprovalJob implements Job {

    private final OrderRepository orderRepository;

    @Override
    public void execute(JobExecutionContext exe) {
        TimerInfo timerInfo = TimerUtils.extractTimerInfo(NotificationBirthDayJob.class, exe);
        Order order = orderRepository.findById(Long.parseLong(timerInfo.getCallback())).get();
        orderRepository.save(order);
    }
}
