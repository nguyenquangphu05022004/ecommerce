package com.example.ecommerce;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.impl.ProductSortServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class EcommerceApplication  {

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}



}
