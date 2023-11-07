package com.michaelspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class PetitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetitionApplication.class, args);
	}

}