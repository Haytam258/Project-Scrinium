package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.AgendaRepository;
import com.mbc.clickclinic.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendaImple implements AgendaService {

    private final AgendaRepository agendaRepository;
    private final RendezvousService rendezvousService;
    private final CongesService congesService;
    private final MedecinService medecinService;
    private final AnnonceService annonceService;
    private final EmailService emailService;

    @Autowired
    public AgendaImple(AgendaRepository agendaRepository,EmailService emailService ,@Lazy RendezvousService rendezvousService, CongesService congesService, @Lazy MedecinService medecinService, @Lazy AnnonceService annonceService){
        this.rendezvousService = rendezvousService;
        this.agendaRepository = agendaRepository;
        this.congesService = congesService;
        this.medecinService = medecinService;
        this.annonceService = annonceService;
        this.emailService = emailService;
    }

    public Agenda createAgenda(Agenda agenda){
        List<Rendezvous> rendezvousList = rendezvousService.Rendezvouss();
        for(Rendezvous rendezvous : rendezvousList){
            if(agenda.getMedecin() == rendezvous.getMedecin() && (rendezvous.getDateRv().isBefore(agenda.getDateFin()) && rendezvous.getDateRv().isAfter(agenda.getDateDebut())) || rendezvous.getDateRv().equals(agenda.getDateDebut()) || rendezvous.getDateRv().equals(agenda.getDateFin())){
                String body = "Bonjour " + rendezvous.getPatient().getNom() + "! \nVotre rendez vous du " + rendezvous.getDateRv() + " à l'heure " +
                        rendezvous.getHeure() + " avec le médecin "+ rendezvous.getMedecin().getNom() + " spécialité "+ rendezvous.getMedecin().getSpecialite()
                        +" a été refusé / supprimé ! car "+agenda.getDescription()+  ", veuillez redemander un rendez vous pour un autre jour " +
                        "! \nLa clinique Scrinium vous souhaite une bonne continuation !\n-Scrinium";
                emailService.sendSimpleMessage(rendezvous.getPatient().getEmail(),body,"Rendez vous refusé / supprimé");
                rendezvousService.deleteRendezvous(rendezvous);
            }
        }
        Annonce annonce = new Annonce();
        annonce.setDateCreation(LocalDate.now());
        annonce.setMessage("Médecin " + agenda.getMedecin().getNom() + " " + agenda.getMedecin().getPrenom() + "ne sera pas disponible du " + agenda.getDateDebut() + " jusqu'à " + agenda.getDateFin());
        annonce.setObjet("Annulation des rendez vous");
        annonceService.saveNotification(annonce);
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

    public void deleteAgenda(Agenda agenda){
        Conges conges = agenda.getConges();
        Medecin medecin = agenda.getMedecin();
        agenda.setMedecin(null);
        agenda.setConges(null);
        if(conges != null){
            conges.setAgenda(null);
            congesService.updateConges(conges);
        }
        if(medecin != null){
            medecin.remove(agenda);
            medecinService.saveMedecin(medecin);
        }
        agendaRepository.delete(agenda);
    }




}
