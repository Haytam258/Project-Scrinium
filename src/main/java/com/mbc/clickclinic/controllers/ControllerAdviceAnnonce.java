package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Annonce;
import com.mbc.clickclinic.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class ControllerAdviceAnnonce {

    private final AnnonceService annonceService;

    @Autowired
    public ControllerAdviceAnnonce(AnnonceService annonceService){
        this.annonceService =  annonceService;
    }

    @ModelAttribute(value = "annonces")
    public List<Annonce> annonceList(){
        return annonceService.getWeeklyAnnonces();
    }

}
