package com.example.ecommerce;

import com.example.ecommerce.domain.entities.ProductBrand;
import com.example.ecommerce.repository.BrandRepository;
import com.example.ecommerce.service.timer.TimerInfo;
import com.example.ecommerce.service.timer.TimerService;
import com.example.ecommerce.service.timer.job.NotificationBirthDayJob;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@AllArgsConstructor
public class EcommerceApplication  {

	private final TimerService timerService;
	private final BrandRepository brandRepository;
	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@PostConstruct
	public void initNotificationUserBirthDay() {
		this.brandRepository.save(ProductBrand.builder().name("quang phu").build());
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
