package com.glearning.employee.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employee.model.User;
import com.glearning.employee.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserAndRolesRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping()
	public Set<User> fetchAllUsers() {
		return this.userService.fetchAllUsers();
	}
}
