package com.example.ecommerce;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EcommerceApplicationTests {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserRepository userRepository;
	@Test
	void isShouldCreateUserTrue() {
//		UserDto user = new UserDto(
//				"irohas2004",
//				"123",
//				"123@gmail.com",
//				"ROLE_USER", null, null, null
//		);
//		UserDto userDto = userService.saveOrUpdate(user);
//		System.out.println(userDto);

	}

}
