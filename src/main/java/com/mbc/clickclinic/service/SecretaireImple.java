package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.Secretaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return secretaireRepository.saveAndFlush(secretaire);
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
