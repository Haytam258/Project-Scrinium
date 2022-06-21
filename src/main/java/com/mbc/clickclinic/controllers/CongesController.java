package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.*;
import com.mbc.clickclinic.service.AnnonceService;
import com.mbc.clickclinic.service.CongesService;
import com.mbc.clickclinic.service.EmailService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CongesController {


    private CongesService congesService;
    private MedecinService medecinService;
    private AnnonceService annonceService;
    private EmailService emailService;

    @Autowired
    public CongesController(MedecinService medecinService, CongesService congesService, @Lazy AnnonceService annonceService, @Lazy EmailService emailService){
        this.medecinService = medecinService;
        this.congesService = congesService;
        this.annonceService = annonceService;
        this.emailService = emailService;
    }


    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/conges/index")
    public String index(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("mesConges", congesService.getCongesByMedecin(medecinService.medecinById(customUser.getId())));
        return "conges/index";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/conges/index")
    public String indexAdmin(Model model){
        List<Conges> congesList = congesService.getAllConges();
        model.addAttribute("congesList", congesList);
        return "conges/indexAdmin";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/conges/create")
    public String create(Model model){
        Conges conges = new Conges();
        model.addAttribute("conges", conges);
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("medecin", medecinService.medecinById(customUser.getId()));
        return "conges/create";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping(path = "/conges/save")
    public String saveConges(@ModelAttribute("conges")Conges conges) {
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(customUser.getId());
        conges.setMedecin(medecin);
        congesService.createConges(conges, conges.getMedecin());
        return "redirect:/conges/index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/conges/accept/{id}")
    public String acceptConges(@PathVariable(value = "id") int id){
        Conges conges = congesService.acceptConges(id);
        Annonce annonce = new Annonce();
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(customUser.getId());
        annonce.setMedecin(medecin);
        annonce.setMessage("Congès a été accepté du " + conges.getDate());
        annonce.setObjet("Congès accepté");
        annonceService.saveNotification(annonce);
        String body = "Bonjour " + conges.getMedecin().getNom() + "! \nVotre demande de congès commençant du " +
                conges.getDate() + " et de durée de "+ conges.getNbrJours() + " jour a été acceptée ! " +
                "! \nLa clinique Scrinium vous souhaite de bonnes vacances !\n-Scrinium";
        emailService.sendSimpleMessage(medecin.getEmail(),body,"Congès accepté !");
        return "redirect:/admin/conges/index";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MEDECIN')")
    @GetMapping("/conges/refuse/{id}")
    public String refuseConges(@PathVariable(value = "id") int id){
        Conges conges = congesService.getCongesById(id);
        Annonce annonce = new Annonce();
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.getMedecinByEmail(customUser.getUsername());
        String body = "Bonjour " + conges.getMedecin().getNom() + "! \nVotre demande de congès commençant du " +
                conges.getDate() + " et de durée de "+ conges.getNbrJours() + " jour a été annulée avec succès ! " +
                "\n-Scrinium";
        if(medecin != null){
            annonce.setMedecin(medecin);
            annonce.setMessage("Votre congès a été refusé ");
            annonce.setObjet("Congès refusé");
            annonceService.saveNotification(annonce);
            congesService.refuseConges(id);
            emailService.sendSimpleMessage(medecin.getEmail(),body,"Congès annulé ");
            return "redirect:/conges/index";
        }
        else {
            annonce.setMedecin(conges.getMedecin());
            body = "Bonjour " + conges.getMedecin().getNom() + "! \nVotre demande de congès commençant du " +
                    conges.getDate() + " et de durée de "+ conges.getNbrJours() + " jour a été refusée ! " +
                    "! \nContactez l'administrateur pour avoir plus de renseignements !\n-Scrinium";
            emailService.sendSimpleMessage(conges.getMedecin().getEmail(),body,"Congès refusé ");
        }
        annonce.setMessage("Votre congès a été refusé ");
        annonce.setObjet("Congès refusé");
        annonceService.saveNotification(annonce);
        congesService.refuseConges(id);
        return "redirect:/admin/conges/index";
    }
}
