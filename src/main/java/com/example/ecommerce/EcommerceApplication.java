package com.example.ecommerce;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EcommerceApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		System.out.println(searchProductDao.findAllByName("nam").size());
	}
}
