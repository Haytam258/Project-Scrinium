package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.SalleDattente;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.SalleDattenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalleDattenteRestController {

    private final SalleDattenteService salleDattenteService;
    private final PatientService patientService;

    @Autowired
    public SalleDattenteRestController(SalleDattenteService salleDattenteService, PatientService patientService){
        this.salleDattenteService = salleDattenteService;
        this.patientService = patientService;
    }

    @GetMapping("/salles")
    public List<SalleDattente> getSalles(){
        return salleDattenteService.getSalles();
    }

    @GetMapping("/salles/{id}")
    public SalleDattente getSalle(@PathVariable Long id){
        return salleDattenteService.getSalleById(id.intValue());
    }

    @PostMapping("/updateSalle")
    public SalleDattente updateSalle(@RequestBody SalleDattente salleDattente){
        return salleDattenteService.saveSalleDattente(salleDattente);
    }

    @PostMapping("/createSalle")
    public SalleDattente createSalle(@RequestBody SalleDattente salleDattente){
        return salleDattenteService.saveSalleDattente(salleDattente);
    }

    @PostMapping("/salles/patients/add")
    public SalleDattente addPatientToSalle(@RequestParam Integer idp, @RequestParam Integer ids){
        return salleDattenteService.addPatientToSalle(salleDattenteService.getSalleById(ids),patientService.PatientById(idp));
    }

    @PostMapping("/salles/patients/delete")
    public void deletePatientFromSalle(@RequestParam Integer idp, @RequestParam Integer ids){
        salleDattenteService.deletePatientFromSalle(salleDattenteService.getSalleById(ids), patientService.PatientById(idp));
    }
}
