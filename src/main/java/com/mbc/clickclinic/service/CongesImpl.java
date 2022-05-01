package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AgendaRepository;
import com.mbc.clickclinic.dao.CongeRepository;
import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.entities.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongesImpl implements CongesService{

    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Override
    public Conges createConges(Conges conges) {
         Agenda agenda = new Agenda();
         agenda.setDateDebut(conges.getDate());
         agenda.setDateFin(conges.getDate().plusDays(conges.getNbrJours()));
         agenda.setDescription("Medecin en conges");
         agendaRepository.save(agenda);
        return congeRepository.save(conges);
    }

    @Override
    public Conges getCongesById(int id) {
        return congeRepository.findById(id).get();
    }

    @Override
    public List<Conges> getCongesByMedecin(Medecin medecin) {
        return congeRepository.findCongesByMedecin(medecin);
    }
}