package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import org.springframework.beans.factory.annotation.Autowired;

public class AnnonceImple implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceImple(AnnonceRepository annonceRepository){
        this.annonceRepository = annonceRepository;
    }
    @Override
    public Annonce saveNotification(Annonce annonce) {
        return annonceRepository.saveAndFlush(annonce);
    }

    @Override
    public void deleteNotification(Annonce annonce) {
        annonceRepository.delete(annonce);
    }

    @Override
    public Annonce updateNotification(Annonce annonce) {
        return null;
    }
}
