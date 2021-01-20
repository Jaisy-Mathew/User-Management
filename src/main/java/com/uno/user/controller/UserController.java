package com.uno.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.uno.user.dto.UserDTO;
import com.uno.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/add")
	public UserDTO addUser(@Valid @RequestBody UserDTO user) {
		return userService.addUser(user);
	}

	@PutMapping(path = "/update/{id}")
	public UserDTO updateUser(@PathVariable(value = "id") String userId, @Valid @RequestBody UserDTO user) {
		Integer id = Integer.parseInt(userId);
		return userService.updateUser(user, id);
	}

	@GetMapping("/details")
	public UserDTO getUser(@RequestParam(value = "userId") String userId) {
		Integer id = Integer.parseInt(userId);
		return userService.getUser(id);
	}

	@GetMapping("/all")
	public List<UserDTO> getAllUsers() {
		return userService.findAll();
	}
}
