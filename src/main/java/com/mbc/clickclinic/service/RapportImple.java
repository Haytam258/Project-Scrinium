package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.RapportRepository;
import com.mbc.clickclinic.entities.Rapport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RapportImple implements RapportService{

    private final RapportRepository rapportRepository;

    @Autowired
    public RapportImple(RapportRepository rapportRepository){
        this.rapportRepository = rapportRepository;
    }
    @Override
    public Rapport saveRapport(Rapport rapport) {
        return rapportRepository.saveAndFlush(rapport);
    }

    @Override
    public void deleteRapport(Rapport rapport) {
        rapportRepository.delete(rapport);
    }

    @Override
    public Rapport updateRapport(Rapport rapport) {
        return null;
    }

    @Override
    public List<Rapport> Rapports() {
        return rapportRepository.findAll();
    }

    @Override
    public Rapport RapportById(Long id) {
        return rapportRepository.findById(id).get();
    }
}
