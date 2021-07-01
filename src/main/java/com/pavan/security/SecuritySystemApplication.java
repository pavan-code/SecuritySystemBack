package com.pavan.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.pavan.models")
@EnableJpaRepositories("com.pavan.repo")
public class SecuritySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySystemApplication.class, args);
	}

}
