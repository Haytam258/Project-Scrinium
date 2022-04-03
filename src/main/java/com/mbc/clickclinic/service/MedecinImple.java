package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.entities.Medecin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MedecinImple implements MedecinService{

    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinImple(MedecinRepository medecinRepository){
        this.medecinRepository = medecinRepository;
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.saveAndFlush(medecin);
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
    public Medecin medecinById(Long id) {
        return medecinRepository.findById(Math.toIntExact(id)).get();
    }
}
