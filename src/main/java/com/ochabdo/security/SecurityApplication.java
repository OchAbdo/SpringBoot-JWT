package com.ochabdo.security;

import static com.ochabdo.security.web.dto.Role.ADMIN;
import static com.ochabdo.security.web.dto.Role.STUDIANT;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ochabdo.security.business.services.AuthenticationService;
import com.ochabdo.security.web.dto.RegisterRequest;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	/* @Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());

			var manager = RegisterRequest.builder()
					.firstname("Studiant")
					.lastname("Studiant")
					.email("Studiant@mail.com")
					.password("password")
					.role(STUDIANT)
					.build();
			System.out.println("Manager token: " + service.register(manager).getToken());

		};
	} */

}
