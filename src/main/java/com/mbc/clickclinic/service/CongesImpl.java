package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AgendaRepository;
import com.mbc.clickclinic.dao.CongeRepository;
import com.mbc.clickclinic.dao.MedecinRepository;
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

    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public Conges createConges(Conges conges, Medecin medecin) {
         Agenda agenda = new Agenda();
         agenda.setDateDebut(conges.getDate());
         agenda.setDateFin(conges.getDate().plusDays(conges.getNbrJours()));
         agenda.setDescription("Medecin en conges");
         agenda.setMedecin(medecin);
         conges.setMedecin(medecin);
         conges.setReponse("En cours");
         agendaRepository.saveAndFlush(agenda);
         congeRepository.saveAndFlush(conges);
         conges.setAgenda(agenda);
         agenda.setConges(conges);
         agendaRepository.saveAndFlush(agenda);
        return congeRepository.saveAndFlush(conges);
    }

    @Override
    public List<Conges> getAllConges(){
        return congeRepository.findAll();
    }

    @Override
    public Conges getCongesById(int id) {
        return congeRepository.findById(id).get();
    }

    @Override
    public List<Conges> getCongesByMedecin(Medecin medecin) {
        return congeRepository.findCongesByMedecin(medecin);
    }

    @Override
    public Conges acceptConges(int id) {
        Conges conges = congeRepository.findById(id).get();
        conges.getAgenda().setStatut(1);
        agendaRepository.saveAndFlush(conges.getAgenda());
        conges.setReponse("Demande Acceptée");
        return congeRepository.save(conges);
    }

    @Override
    public Conges refuseConges(int id) {
        Conges conges = congeRepository.findById(id).get();
        agendaRepository.delete(conges.getAgenda());
        conges.setReponse("Demande Refusée");
        return congeRepository.save(conges);
    }
}
