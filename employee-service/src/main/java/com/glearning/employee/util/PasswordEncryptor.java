package com.glearning.employee.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		
		String plainText="welcome";
		
		String encodedText=passwordEncoder.encode(plainText);
				
		System.out.println(encodedText);
		
		System.out.println(passwordEncoder.matches(plainText, encodedText));

	}

}
