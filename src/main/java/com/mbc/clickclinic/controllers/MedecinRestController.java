package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MedecinRestController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinRestController(MedecinService medecinService){
        this.medecinService = medecinService;
    }

    @GetMapping("/createMedecin")
    public String createMedecin(Model model){
        model.addAttribute("medecin", new Medecin());
        return "medecin/createMedecin";
    }

    @PostMapping("/createMedecin")
    public String createMedecin(@ModelAttribute Medecin medecin, Model model){
        medecinService.saveMedecin(medecin,model);
        model.addAttribute("medecin", medecin);
        return "medecin/createMedecin";
    }

    @PostMapping("/modifyMedecin")
    public Medecin modifyMedecin(@RequestBody Medecin medecin){
        return medecinService.updateMedecin(medecin);
    }

    @PostMapping("/deleteMedecin/{id}")
    public void deleteMedecin(@PathVariable Long id){
        medecinService.deleteMedecin(medecinService.medecinById(id));
    }

    @GetMapping("/medecins/{id}")
    public Medecin getMedecin(@PathVariable Long id){
        return medecinService.medecinById(id);
    }

    @GetMapping("/medecins")
    public List<Medecin> getMedecins(){
        return medecinService.medecins();
    }
}
