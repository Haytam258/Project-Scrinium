package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import com.mbc.clickclinic.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AnnonceRestController {

    private final AnnonceService annonceService;

    @Autowired
    public AnnonceRestController(AnnonceService annonceService){
        this.annonceService = annonceService;
    }

    @PostMapping("/createAnnonce")
    public String createAnnonce(@ModelAttribute(value = "newAnnonce") Annonce annonce, Model model){
        annonce.setDateCreation(LocalDate.now());
        if(annonceService.saveNotification(annonce) != null){
            model.addAttribute("annonceCreated", "Annonce créée avec succès !");
        }
        else {
            model.addAttribute("annonceFail", "Veuillez vérifier les informations saisies et respectez la longeur du message et objet (255 caractères)");
        }
        return "annonce/createAnnonce";
    }

    @GetMapping("/createAnnonce")
    public String createAnnonce(Model model){
        model.addAttribute("newAnnonce", new Annonce());
        return "annonce/createAnnonce";
    }

    @PostMapping("/modifyAnnonce")
    public Annonce modifyAnnonce(@RequestBody Annonce annonce){
        return annonceService.updateNotification(annonce);
    }

    @GetMapping("/annonces")
    public String getAnnonces(Model model){
        model.addAttribute("annonceList",annonceService.getAllAnnonce());
        return "annonce/annonceList";
    }

    @GetMapping("/annonces/{id}")
    public Annonce getAnnonce(@PathVariable Long id){
        return annonceService.getAnnonce(id.intValue());
    }

    @GetMapping("/deleteAnnonce/{id}")
    public String deleteAnnonce(@PathVariable Integer id){
        annonceService.deleteNotification(annonceService.getAnnonce(id));
        return "redirect:/annonces";
    }
}
