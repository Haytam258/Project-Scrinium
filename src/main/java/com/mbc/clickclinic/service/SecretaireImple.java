package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PersonneRepository;
import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.Secretaire;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SecretaireImple implements SecretaireService{

    private final SecretaireRepository secretaireRepository;
    private final PersonneRepository personneRepository;
    private final MedecinService medecinService;
    private final PatientService patientService;

    @Autowired
    public SecretaireImple(SecretaireRepository secretaireRepository, @Lazy MedecinService medecinService, @Lazy PatientService patientService, PersonneRepository personneRepository){
        this.secretaireRepository = secretaireRepository;
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.personneRepository = personneRepository;
    }
    @Override
    public Secretaire saveSecretaire(Secretaire secretaire) {
        secretaire.setRole(GeneralRole.SECRETAIRE.getRole());
        return secretaireRepository.saveAndFlush(secretaire);
    }

    public Secretaire saveSecretaire(Secretaire secretaire, Model model){
        if(medecinService.getMedecinByEmail(secretaire.getEmail()) != null || personneRepository.findPersonneByEmail(secretaire.getEmail()) != null ||
                patientService.getPatientByEmail(secretaire.getEmail()) != null || secretaireRepository.findSecretaireByEmail(secretaire.getEmail()) != null){
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
    public Secretaire findSecretaireByEmail(String email) {
        return secretaireRepository.findSecretaireByEmail(email);
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
