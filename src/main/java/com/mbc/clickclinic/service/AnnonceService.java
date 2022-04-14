package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Annonce;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AnnonceService {
    Annonce saveNotification(Annonce annonce);
    void deleteNotification(Annonce annonce);
    Annonce updateNotification(Annonce annonce);
    public List<Annonce> getAllAnnonce();
    public Annonce getAnnonce(Long id);
}
