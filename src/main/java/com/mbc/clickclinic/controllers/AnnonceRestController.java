package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnonceRestController {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceRestController(AnnonceRepository annonceRepository){
        this.annonceRepository = annonceRepository;
    }

    @PostMapping("/createAnnonce")
    public Annonce createAnnonce(@RequestBody Annonce annonce){
        return annonceRepository.saveAndFlush(annonce);
    }

    @PostMapping("/modifyAnnonce/{id}")
    public Annonce modifyAnnonce(@RequestBody Annonce annonce, @PathVariable Long id){
        Annonce ann = annonceRepository.findById(id).get();
        ann.setDateCreation(annonce.getDateCreation());
        ann.setId(annonce.getId());
        ann.setObjet(annonce.getObjet());
        ann.setMessage(annonce.getMessage());
        return annonceRepository.saveAndFlush(ann);
    }

    @GetMapping("/annonces")
    public List<Annonce> getAnnonces(){
        return annonceRepository.findAll();
    }
}
