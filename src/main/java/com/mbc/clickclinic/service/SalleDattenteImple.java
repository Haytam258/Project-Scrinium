package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.SalleDattentRepository;
import com.mbc.clickclinic.entities.SalleDattente;
import org.springframework.beans.factory.annotation.Autowired;

public class SalleDattenteImple implements SalleDattenteService{

    private final SalleDattentRepository salleDattentRepository;

    @Autowired
    public SalleDattenteImple(SalleDattentRepository salleDattentRepository){
        this.salleDattentRepository = salleDattentRepository;
    }
    @Override
    public SalleDattente saveSalleDattente(SalleDattente salleDattente) {
        return salleDattentRepository.saveAndFlush(salleDattente);
    }

    @Override
    public void deleteSalleDattente(SalleDattente salleDattente) {
        salleDattentRepository.delete(salleDattente);
    }
}
