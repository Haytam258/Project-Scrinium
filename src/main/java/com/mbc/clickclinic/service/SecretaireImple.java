package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.Secretaire;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SecretaireImple implements SecretaireService{

    private final SecretaireRepository secretaireRepository;

    @Autowired
    public SecretaireImple(SecretaireRepository secretaireRepository){
        this.secretaireRepository = secretaireRepository;
    }
    @Override
    public Secretaire saveSecretaire(Secretaire secretaire) {
        secretaire.setRole(GeneralRole.SECRETAIRE.getRole());
        return secretaireRepository.saveAndFlush(secretaire);
    }

    public Secretaire saveSecretaire(Secretaire secretaire, Model model){
        if(secretaireRepository.findSecretaireByEmail(secretaire.getEmail()) != null){
            model.addAttribute("emailExists", "Cet Email existe déjà !");
            return null;
        }
        if(secretaire.getNom().matches(".*[0-9].*") || secretaire.getPrenom().matches(".*[0-9].*")){
            model.addAttribute("numbers", "Nom ou prénom contient un nombre !");
            return null;
        }
        return saveSecretaire(secretaire);
    }

    @Override
    public void deleteSecretaire(Secretaire secretaire) {
        secretaireRepository.delete(secretaire);
    }

    @Override
    public Secretaire updateSecretaire(Secretaire secretaire) {
        return null;
    }

    @Override
    public Secretaire findSecretaireBykw(String kw) {
        return null;
    }

    @Override
    public Secretaire findSecretaireByNom(String sName) {
        return secretaireRepository.findSecretaireByNom(sName);
    }

    @Override
    public List<Secretaire> Secretaires() {
        return secretaireRepository.findAll();
    }

    @Override
    public Secretaire SecretaireById(int id) {
        return secretaireRepository.findById(id).get();
    }
}
