package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.OrdonnanceRepository;
import com.mbc.clickclinic.entities.Ordonnance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrdonnanceImple implements OrdonnanceService{

    private final OrdonnanceRepository ordonnanceRepository;

    @Autowired
    public OrdonnanceImple(OrdonnanceRepository ordonnanceRepository){
        this.ordonnanceRepository = ordonnanceRepository;
    }

    @Override
    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        return ordonnanceRepository.saveAndFlush(ordonnance);
    }

    @Override
    public void deleteOrdonnance(Ordonnance ordonnance) {
        ordonnanceRepository.delete(ordonnance);
    }

    @Override
    public Ordonnance updateOrdonnance(Ordonnance ordonnance) {
        return null;
    }

    @Override
    public List<Ordonnance> Ordonnances() {
        return ordonnanceRepository.findAll();
    }

    @Override
    public Ordonnance OrdonnanceById(Long id) {
        return ordonnanceRepository.findById(id).get();
    }
}
