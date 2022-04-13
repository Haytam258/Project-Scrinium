package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import org.springframework.beans.factory.annotation.Autowired;

public class AnnonceImple implements AnnonceService {

    private final AnnonceRepository notificationRepository;

    @Autowired
    public AnnonceImple(AnnonceRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public Annonce saveNotification(Annonce annonce) {
        return notificationRepository.saveAndFlush(annonce);
    }

    @Override
    public void deleteNotification(Annonce annonce) {
        notificationRepository.delete(annonce);
    }

    @Override
    public Annonce updateNotification(Annonce annonce) {
        return null;
    }
}
