package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@SpringBootApplication
@RestController
public class PetitionApplication {

	@RequestMapping("/")
	public String home() {
		return "Home";
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("home.html");
//		return modelAndView;
	}

	@RequestMapping("/view")
	public String viewPetitions() {
		return "View";
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("view-petitions.html");
//		return modelAndView;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PetitionApplication.class);
		app.run(args);
	}

}