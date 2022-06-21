package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.service.MedecinDetailsService;
import com.mbc.clickclinic.service.PatientDetailsService;
import com.mbc.clickclinic.service.SecretaireDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    private final PatientDetailsService patientDetailsService;
    private final MedecinDetailsService medecinDetailsService;
    private final SecretaireDetailsService secretaireDetailsService;

    @Autowired
    public SecurityController(PatientDetailsService patientDetailsService, SecretaireDetailsService secretaireDetailsService, MedecinDetailsService medecinDetailsService){
        this.medecinDetailsService = medecinDetailsService;
        this.patientDetailsService = patientDetailsService;
        this.secretaireDetailsService = secretaireDetailsService;
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return "homepage";
    }


}
