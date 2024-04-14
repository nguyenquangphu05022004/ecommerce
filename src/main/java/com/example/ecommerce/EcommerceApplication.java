package com.example.ecommerce;

import com.example.ecommerce.dao.impl.CouponEventDao;
import com.example.ecommerce.entity.Coupon;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {


	}
}
