package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import com.mbc.clickclinic.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnonceRestController {

    private final AnnonceService annonceService;

    @Autowired
    public AnnonceRestController(AnnonceService annonceService){
        this.annonceService = annonceService;
    }

    @PostMapping("/createAnnonce")
    public Annonce createAnnonce(@RequestBody Annonce annonce){
        return annonceService.saveNotification(annonce);
    }

    @PostMapping("/modifyAnnonce")
    public Annonce modifyAnnonce(@RequestBody Annonce annonce){
        return annonceService.updateNotification(annonce);
    }

    @GetMapping("/annonces")
    public List<Annonce> getAnnonces(){
        return annonceService.getAllAnnonce();
    }

    @GetMapping("/annonces/{id}")
    public Annonce getAnnonce(@PathVariable Long id){
        return annonceService.getAnnonce(id.intValue());
    }

    @PostMapping("/deleteAnnonce/{id}")
    public void deleteAnnonce(@PathVariable Long id){
        annonceService.deleteNotification(annonceService.getAnnonce(id.intValue()));
    }
}
