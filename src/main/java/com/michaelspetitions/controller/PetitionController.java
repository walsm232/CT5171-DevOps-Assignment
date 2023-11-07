package com.michaelspetitions.controller;

import com.michaelspetitions.model.Petition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class PetitionController {

    private final Logger LOG = LoggerFactory.getLogger(PetitionController.class);
    private final Map<String, Petition> petitionsMap = new ConcurrentHashMap<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
       return "home";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPetitionsPage(Model model) {
        model.addAttribute("petitionsMap", petitionsMap);
        return "view-petitions";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPetitionsPage(ModelMap model) {
        model.addAttribute("petition", new Petition());
        return "create-petition";
    }

    @PostMapping("/create")
    public String createPetition(@ModelAttribute("petition") Petition petition) {

        String petitionId = UUID.randomUUID().toString();
        petition.setId(petitionId);

        petitionsMap.put(petitionId, petition);

        // LOG.info("Id: {}, Petition Name: {}, description: {}, scope:{}", petition.getId(), petition.getName(), petition.getDescription(), petition.getScope());

        return "petition-created";
    }

}