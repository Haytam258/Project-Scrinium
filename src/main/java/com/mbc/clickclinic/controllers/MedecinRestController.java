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
        if(medecinService.saveMedecin(medecin,model) != null){
            model.addAttribute("medecinSuccess","Médecin créé avec succès !");
        }
        else {
            model.addAttribute("medecinFail","Veuillez vérifier les informations saisies !");
        }
        model.addAttribute("medecin", medecin);
        return "medecin/createMedecin";
    }

    @PostMapping("/modifyMedecin")
    public Medecin modifyMedecin(@RequestBody Medecin medecin){
        return medecinService.updateMedecin(medecin);
    }

    @GetMapping("/deleteMedecin/{id}")
    public String deleteMedecin(@PathVariable Integer id){
        medecinService.deleteMedecin(medecinService.medecinById(id));
        return "redirect:/medecins";
    }

    @GetMapping("/medecins/{id}")
    public Medecin getMedecin(@PathVariable Integer id){
        return medecinService.medecinById(id);
    }

    @GetMapping("/medecins")
    public String getMedecins(Model model){
        model.addAttribute("allMedecins",medecinService.medecins());
        return "medecin/medecinList";
    }
}
