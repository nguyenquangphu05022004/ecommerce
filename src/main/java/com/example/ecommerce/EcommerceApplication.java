package com.example.ecommerce;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import com.example.ecommerce.service.impl.ProductSortServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class EcommerceApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Product> products =
				productRepository.findAll();
		new ProductSortServiceImpl().sortByDefault(products);
		System.out.println(products);
	}


}
