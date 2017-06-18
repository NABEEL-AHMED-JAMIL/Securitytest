package com.ballistic.security.Securitytest;

import com.ballistic.security.Securitytest.service.FectchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuritytestApplication {

	@Autowired
	FectchDataService fectchDataService;

	public static void main(String[] args) {
		SpringApplication.run(SecuritytestApplication.class, args);
	}
}
