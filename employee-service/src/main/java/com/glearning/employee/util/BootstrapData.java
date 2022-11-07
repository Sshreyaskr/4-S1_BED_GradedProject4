package com.glearning.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employee.model.Employee;
import com.glearning.employee.model.Role;
import com.glearning.employee.model.User;
import com.glearning.employee.repository.EmployeeRepository;
import com.glearning.employee.repository.UserRepository;

@Configuration
public class BootstrapData {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent readyEvent) {
		Employee ajay=new Employee();
		ajay.setFirstName("Ajay");
		ajay.setLastName("Kumar");
		ajay.setEmail("ajay@gmail.com");
		this.empRepository.save(ajay);
		
		Employee bhaskar=new Employee();
		bhaskar.setFirstName("Bhaskar");
		bhaskar.setLastName("Kumar");
		bhaskar.setEmail("Bhaskar@gmail.com");		
		this.empRepository.save(bhaskar);
		
		//adding the user and roles to the DB
		
		User vinay=new User();
		vinay.setUsername("vinay");
		vinay.setPassword(this.passwordEncoder.encode("welcome"));
		vinay.setEmailAddress("vinay@gmail.com");;
		
		Role kiranRole=new Role();
		kiranRole.setRoleName("USER");
		
		Role vinayRole=new Role();
		vinayRole.setRoleName("ADMIN");	
		
		vinayRole.setUser(vinay);
		vinay.addRole(vinayRole);
		
		User kiran=new User();
		kiran.setUsername("kiran");
		kiran.setPassword(this.passwordEncoder.encode("welcome"));
		kiran.setEmailAddress("kiran@gmail.com");
		kiran.addRole(kiranRole);
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);		
	}
	
}
