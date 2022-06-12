package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AgendaRepository;
import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaImple implements AgendaService {

    private final AgendaRepository agendaRepository;
    private final RendezvousService rendezvousService;

    @Autowired
    public AgendaImple(AgendaRepository agendaRepository, @Lazy RendezvousService rendezvousService){
        this.rendezvousService = rendezvousService;
        this.agendaRepository = agendaRepository;
    }

    public Agenda createAgenda(Agenda agenda){
        List<Rendezvous> rendezvousList = rendezvousService.Rendezvouss();
        for(Rendezvous rendezvous : rendezvousList){
            if(agenda.getMedecin() == rendezvous.getMedecin() && rendezvous.getDateRv().isBefore(agenda.getDateFin()) && rendezvous.getDateRv().isAfter(agenda.getDateDebut())){
                rendezvousService.deleteRendezvous(rendezvous);
                // Créer une annonce qui décrit la création de l'agenda et l'annulation des rendez vous de cette date.
            }
        }
        return agendaRepository.saveAndFlush(agenda);
    }

    public Agenda getAgendaById(int id){
        return agendaRepository.findById(id).get();
    }

    public List<Agenda> getAgendaByMedecin(Medecin medecin){
        return agendaRepository.findAgendaByMedecin(medecin);
    }

    public List<Agenda> allAgenda(){
        return agendaRepository.findAll();
    }





}
