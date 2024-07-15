package com.example.ecommerce;

import com.example.ecommerce.repository.dao.SearchProductDao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
