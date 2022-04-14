package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Medecin;
import org.springframework.stereotype.Service;
import java.util.List;


public interface MedecinService {
    Medecin saveMedecin(Medecin medecin);
    void deleteMedecin(Medecin medecin);
    Medecin updateMedecin(Medecin medecin);
    Medecin findMedecinBykw(String kw);
    Medecin findMedecinByNom(String mName);
    List<Medecin> medecins();
    Medecin medecinById(Long id);
}
