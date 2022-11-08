package com.glearning.employee.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.glearning.employee.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private final DomainUserDetailsService userDetailsService;
	
	//authentication
	@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {		
		 //configure users
		         authenticationManagerBuilder
		                 .userDetailsService(this.userDetailsService)
		                 .passwordEncoder(passwordEncoder());
    }
	
	//authorization
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // configure authorization rules here
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
       
        httpSecurity.authorizeRequests()
                	.antMatchers(GET,"/api/employees/**")
                		.permitAll()
                .antMatchers("/h2-console/**", "/login/**", "/contact-us/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(POST,"/api/employees/**")
                	.hasRole("ADMIN")
                .antMatchers(PUT,"/api/employees/**")
                	.hasRole("ADMIN")
                .antMatchers(DELETE,"/api/employees/**")
                	.hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
               .and()
                /*
                   Set the sessioncreation policy to avoid using cookies for authentication
                   https://stackoverflow.com/questions/50842258/spring-security-caching-my-authentication/50847571
                 */
                .sessionManagement().sessionCreationPolicy(STATELESS); 
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
