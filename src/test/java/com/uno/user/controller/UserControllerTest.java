package com.uno.user.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.uno.user.dto.UserDTO;


@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private UserController userController;

	//@Test
	public void contextLoads() {
		assertThat(userController).isNotNull();
	}

	//@Test
	public void testgetUserById(){	
		UserDTO userDTO = userController.getUser("123");
		assertEquals("123",userDTO.getUserId());		
	}

	//@Test
	public void testNonNumericUserId() {		
		assertThrows(NumberFormatException.class, ()-> {
			userController.getUser("abc");
		});
	}

}
