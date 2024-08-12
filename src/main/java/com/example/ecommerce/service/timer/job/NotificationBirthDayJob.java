package com.example.ecommerce.service.timer.job;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Notification;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.repository.NotificationRepository;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.ecommerce.domain.entities.EntityType.Type.USER;
import static com.example.ecommerce.service.event.listener.NotificationActionType.HAPPY_BIRTHDAY;


@Component
@RequiredArgsConstructor
public class NotificationBirthDayJob implements Job {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;

    @Override
    public void execute(JobExecutionContext exe) {
        List<User> users = userRepository.findAll();
        if (CollectionUtils.isEmpty(users)) {
            users.stream().forEach(user -> {
                LocalDateTime birthOfDate = user.getBirthOfDate();
                if (birthOfDate != null &
                        birthOfDate.getDayOfMonth() == LocalDateTime.now().getDayOfMonth() &&
                        birthOfDate.getMonthValue() == LocalDateTime.now().getMonthValue()) {
                    Notification notification = Notification.builder()
                            .entityType(EntityType.builder().entityId(user.getId()).entityType(USER).build())
                            .notificationActionType(HAPPY_BIRTHDAY)
                            .message("Today is your birthday, Happy Birthday")
                            .build();
                    notificationRepository.save(notification);
                    simpMessagingTemplate.convertAndSendToUser(user.getId().toString(), "/user/topic/private-message", notification);
                }
            });
        }
    }
}
