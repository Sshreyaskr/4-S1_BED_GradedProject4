package com.glearning.employee.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glearning.employee.model.User;
import com.glearning.employee.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	//method to return all the users and their roles
		public Set<User> fetchAllUsers() {
			return new HashSet<>(this.userRepository.findAll());
		}

}
