package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Medecin;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


public interface MedecinService {
    Medecin saveMedecin(Medecin medecin);
    void deleteMedecin(Medecin medecin);
    Medecin findMedecinByNom(String mName);
    List<Medecin> medecins();
    Medecin medecinById(Integer id);
    Medecin saveMedecin(Medecin medecin, Model model);
    public Medecin getMedecinByEmail(String email);
}
