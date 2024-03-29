package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.Secretaire;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


public interface SecretaireService {
    Secretaire saveSecretaire(Secretaire medecin);
    void deleteSecretaire(Secretaire medecin);
    Secretaire updateSecretaire(Secretaire medecin);
    Secretaire findSecretaireBykw(String kw);
    Secretaire findSecretaireByNom(String mName);
    List<Secretaire> Secretaires();
    Secretaire SecretaireById(int id);
    Secretaire saveSecretaire(Secretaire secretaire, Model model);
    Secretaire findSecretaireByEmail(String email);
}
