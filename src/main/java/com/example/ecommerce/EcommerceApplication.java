package com.example.ecommerce;

import com.example.ecommerce.service.timer.TimerInfo;
import com.example.ecommerce.service.timer.TimerService;
import com.example.ecommerce.service.timer.job.NotificationBirthDayJob;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EcommerceApplication  {

	private final TimerService timerService;

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@PostConstruct
	public void initNotificationUserBirthDay() {
		timerService.schedulerJob(
				NotificationBirthDayJob.class,
				TimerInfo.builder()
						.callback(null)
						.fireCount(1)
						.repeatForever(true)
						.offsetMinis(5000)
						.repeatIntervalMinis(1 * 24 * 60 * 60 * 1000)
						.build());
	}
}
