package com.michaelspetitions.controller;

import com.michaelspetitions.model.Petition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class PetitionController {

    private final Logger LOG = LoggerFactory.getLogger(PetitionController.class);
    private Map<String, Petition> petitions = new ConcurrentHashMap<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
       return "home";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPetitionsPage() {
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

        petitions.put(petitionId, petition);

        // LOG.info("Id: {}, Petition Name: {}, description: {}, scope:{}", petition.getId(), petition.getName(), petition.getDescription(), petition.getScope());

        return "petition-created";
    }

}