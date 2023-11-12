package com.michaelspetitions.controller;

import com.michaelspetitions.model.Petition;
import com.michaelspetitions.model.PetitionSearch;
import com.michaelspetitions.model.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


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

        return "petition-created";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPetitionsPage(ModelMap model) {
        model.addAttribute("petitionSearch", new PetitionSearch());
        return "search-petitions";
    }

    @PostMapping("/search/results")
    public String searchPetitions(@ModelAttribute("petitionSearch") PetitionSearch petitionSearch, ModelMap model) {
        String query = petitionSearch.getQuery().toLowerCase();

        List<Petition> results = petitionsMap.values().stream()
                .filter(petition ->
                        petition.getName().toLowerCase().contains(query) ||
                        petition.getDescription().toLowerCase().contains(query) ||
                        petition.getScope().toLowerCase().contains(query))
                .collect(Collectors.toList());

        model.addAttribute("results", results);

        return "search-results";
    }

    @RequestMapping("/petition/{id}")
    public String getPetitionDetails(@PathVariable("id") String id, ModelMap model) {
        Petition petition = petitionsMap.get(id);

        model.addAttribute("petition", petition);

        return "petition-details";
    }

    @PostMapping("/petition/{id}/sign")
    public String signPetition(@PathVariable("id") String id, @ModelAttribute("signature") Signature signature) {
        Petition petition = petitionsMap.get(id);

        petition.addSignature(signature);

        return "redirect:/petition/" + id;
    }

}