package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AgendaRepository;
import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaImple implements AgendaService {

    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaImple(AgendaRepository agendaRepository){
        this.agendaRepository = agendaRepository;
    }

    public Agenda createAgenda(Agenda agenda){
        return agendaRepository.saveAndFlush(agenda);
    }

    public Agenda getAgendaById(int id){
        return agendaRepository.findById(id).get();
    }

    public List<Agenda> getAgendaByMedecin(Medecin medecin){
        return agendaRepository.findAgendaByMedecin(medecin);
    }





}
