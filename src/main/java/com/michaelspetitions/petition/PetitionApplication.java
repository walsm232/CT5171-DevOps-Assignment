package com.michaelspetitions.petition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@SpringBootApplication
@RestController
public class PetitionApplication {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home.html");
		return modelAndView;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewPetitionsPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view-petitions.html");
		return modelAndView;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createPetitionsPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("create-petition.html");
		return modelAndView;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PetitionApplication.class);
		app.run(args);
	}

}