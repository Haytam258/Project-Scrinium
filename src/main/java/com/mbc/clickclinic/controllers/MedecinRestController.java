package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedecinRestController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinRestController(MedecinService medecinService){
        this.medecinService = medecinService;
    }

    @PostMapping("/createMedecin")
    public Medecin createMedecin(@RequestBody Medecin medecin){
        return medecinService.saveMedecin(medecin);
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
