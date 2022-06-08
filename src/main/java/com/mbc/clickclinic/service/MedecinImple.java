package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MedecinImple implements MedecinService{

    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinImple(MedecinRepository medecinRepository){
        this.medecinRepository = medecinRepository;
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        medecin.setRole(GeneralRole.MEDECIN.getRole());
        return medecinRepository.saveAndFlush(medecin);
    }

    /*public Patient savePatient(Patient patient, Model model){
        if(patientRepository.findPatientByEmail(patient.getEmail()) != null){
            model.addAttribute("emailExist", "Email exists !");
            return null;
        }
        return patientRepository.saveAndFlush(patient);
    }*/

    public Medecin saveMedecin(Medecin medecin, Model model){
        if(medecinRepository.findMedecinByEmail(medecin.getEmail()) != null){
            model.addAttribute("emailMedecinExist", "Email exits !");
            return null;
        }
        if(medecin.getNom().matches(".*[0-9].*") || medecin.getPrenom().matches(".*[0-9].*")){
            model.addAttribute("numbers", "Nom ou prénom contient un nombre !");
            return null;
        }
        return saveMedecin(medecin);
    }

    @Override
    public void deleteMedecin(Medecin medecin) {
        medecinRepository.delete(medecin);
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        return null;
    }

    //What is kw ?
    @Override
    public Medecin findMedecinBykw(String kw) {
        return null;
    }

    @Override
    public Medecin findMedecinByNom(String mName) {
        return medecinRepository.findMedecinByNom(mName);
    }

    @Override
    public List<Medecin> medecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin medecinById(Integer id) {
        return medecinRepository.findById(id).get();
    }
}
