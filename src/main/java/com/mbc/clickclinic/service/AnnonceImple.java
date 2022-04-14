package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return annonceRepository.saveAndFlush(annonce);
    }

    public List<Annonce> getAllAnnonce(){
        return annonceRepository.findAll();
    }

    public Annonce getAnnonce(Long id){
        return annonceRepository.findById(id).get();
    }
}
