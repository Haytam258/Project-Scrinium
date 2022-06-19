package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.SalleDattente;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.SalleDattenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SalleDattenteRestController {

    private final SalleDattenteService salleDattenteService;
    private final PatientService patientService;
    private final MedecinService medecinService;

    @Autowired
    public SalleDattenteRestController(SalleDattenteService salleDattenteService, PatientService patientService, MedecinService medecinService){
        this.salleDattenteService = salleDattenteService;
        this.patientService = patientService;
        this.medecinService = medecinService;
    }

    @PreAuthorize("hasAnyAuthority('MEDECIN','SECRETAIRE','ADMIN')")
    @GetMapping("/salles")
    public String getSalles(Model model){
        List<SalleDattente> salles = salleDattenteService.getSalles();
        model.addAttribute("salles", salles);
        model.addAttribute("medecins", medecinService.medecins());
        return "salleAttente/sallesDattentes";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createSalle")
    public String createSalle(Model model){
        model.addAttribute("salleDattente", new SalleDattente());
        model.addAttribute("medecins", medecinService.medecins());
        return "salleAttente/createSalle";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createSalle")
    public String createSalle(@ModelAttribute SalleDattente salleDattente, Model model){
        Medecin medecin = salleDattente.getMedecin();
        if(medecin.getSalleDattente() != null){
            model.addAttribute("salleMedecinExist", "La salle du médecin existe déjà");
        }
        else {
            medecin.setSalleDattente(salleDattente);
            medecinService.saveMedecin(medecin);
            model.addAttribute("salleMedecinDone", "Salle d'attente a été créée avec succès !");
        }
        model.addAttribute("medecins", medecinService.medecins());
        return "salleAttente/createSalle";
    }

    @PreAuthorize("hasAnyAuthority('MEDECIN','SECRETAIRE')")
    @GetMapping("/salles/patients/add")
    public String addPatientToSalle(@RequestParam Integer idp, @RequestParam Integer ids, Model model){
        salleDattenteService.addPatientToSalle(salleDattenteService.getSalleById(ids),patientService.PatientById(idp));
        List<SalleDattente> salles = salleDattenteService.getSalles();
        model.addAttribute("salles", salles);
        return "redirect:/salles";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/salles/patients/delete")
    public String deletePatientFromSalle(@RequestParam Integer idp, @RequestParam Integer ids, Model model){
        salleDattenteService.deletePatientFromSalle(salleDattenteService.getSalleById(ids), patientService.PatientById(idp));
        List<SalleDattente> salles = salleDattenteService.getSalles();
        model.addAttribute("salles", salles);
        return "redirect:/salles";
    }
}
