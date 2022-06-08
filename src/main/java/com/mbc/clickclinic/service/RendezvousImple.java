package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.RendezvousRepository;
import com.mbc.clickclinic.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class RendezvousImple implements RendezvousService{

    private final RendezvousRepository rendezvousRepository;
    private final PatientService patientService;
    private final MedecinService medecinService;
    private final AgendaService agendaService;

    //Lazy in order to break the bean dependency cycle.
    @Autowired
    public RendezvousImple(RendezvousRepository rendezvousRepository, @Lazy PatientService patientService, MedecinService medecinService, AgendaService agendaService){
        this.rendezvousRepository = rendezvousRepository;
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.agendaService = agendaService;
    }
    public List<Rendezvous> rendezvousByDate(LocalDate date){
        return rendezvousRepository.findRendezvousByDateRv(date);
    }

    public List<Rendezvous> rendezvousByDateAndStatut(LocalDate date, Integer statut){
        return rendezvousRepository.findRendezvousByDateRvAndStatut(date,statut);
    }

    public List<Rendezvous> rendezvousByStatut(Integer statut){
        return rendezvousRepository.findRendezvousByStatut(statut);
    }

    @Override
    public Rendezvous saveRendezvous(Rendezvous rendezvous, Model model) {
        List<Rendezvous> rend = rendezvousRepository.findRendezvousByDateRv(rendezvous.getDateRv());
        if(rend.size() != 0){
            for (Iterator<Rendezvous> rendezvousIterator = rend.iterator(); rendezvousIterator.hasNext();) {
                Rendezvous rendez = rendezvousIterator.next();
                //On supposera qu'un rendezvous passera 1 heure maximum, donc on vérifie que la date est libre avant de l'insérer. Le statut 0 indique que le rendezvous n'est pas
                //encore fait.
                // REST : if(rendezvous.getHeure() <= rendez.getHeure()+ 1 && rendezvous.getHeure() >= rendez.getHeure() - 1 && rendez.getStatut() == 0 && rendezvous.getStatut() == 0)
                //HTML Transition : if(rendezvous.getHeure().getHour() <= rendez.getHeure().getHour() + 1 && rendezvous.getHeure().getHour() >= rendez.getHeure().getHour() - 1 && rendez.getStatut() == 0 && rendezvous.getStatut() == 0){
                if(Math.abs(ChronoUnit.MINUTES.between(rendezvous.getHeure(), rendez.getHeure())) <= 30 && rendez.getStatut() == 0 && rendezvous.getStatut() == 0){
                    model.addAttribute("rendezvousMinute", "les rendez vous doivent etre séparés par 30 minutes !");
                    return null;
                }
                if(rendezvous.getPatient() == rendez.getPatient() && rendezvous.getMedecin() == rendez.getMedecin() && rendezvous.getDateRv().isEqual(rendez.getDateRv())){
                    model.addAttribute("rendezvousAlready", "Ce patient a déjà un rendez vous avec ce médecin le jour que vous avez choisi !");
                    return null;
                }
            }
        }
        if(rendezvous.getDateRv().isBefore(LocalDate.now())){
            System.out.println("We're here");
            model.addAttribute("rendezVousImpossible", "Un rendez vous avant aujourd'hui est impossible !");
            return null;
        }
        List<Agenda> agendaList = agendaService.getAgendaByMedecin(rendezvous.getMedecin());
        if(agendaList.size() != 0){
            for(Agenda agenda : agendaList){
                if(rendezvous.getDateRv().isAfter(agenda.getDateDebut()) && rendezvous.getDateRv().isBefore(agenda.getDateFin()) && agenda.getStatut() == 1){
                    model.addAttribute("agendaConstraint",  agenda.getDescription());
                    return null;
                }
            }
        }

        return rendezvousRepository.saveAndFlush(rendezvous);
    }

    @Override
    public void deleteRendezvous(Rendezvous rendezvous) {
        rendezvousRepository.delete(rendezvous);
    }

    @Override
    public Rendezvous updateRendezvous(Rendezvous rendezvous) {
        return rendezvousRepository.saveAndFlush(rendezvous);
    }

    @Override
    public List<Rendezvous> Rendezvouss() {
        /*List<Rendezvous> rendezvousList = rendezvousRepository.findAll();
        for(Rendezvous rendezvous : rendezvousList){
            if(rendezvous.getDateRv().isBefore(LocalDate.now()) && rendezvous.getStatut() == 0){
                rendezvous.setMedecin(null);
                rendezvous.setPatient(null);
                rendezvous.setConsultation(null);
                deleteRendezvous(rendezvous);
            }
        }*/
        List<Rendezvous> rendezvousList = rendezvousRepository.findAll();
        for(Iterator<Rendezvous> rendezvousIterator = rendezvousList.listIterator(); rendezvousIterator.hasNext();){
            Rendezvous rendezvous = rendezvousIterator.next();
            if(rendezvous.getStatut() == 2){
                rendezvousIterator.remove();
            }
            else {
                continue;
            }
        }
        return rendezvousList;
    }

    @Override
    public Rendezvous RendezvousById(int id) {
        return rendezvousRepository.findById(id).get();
    }

    @Override
    public List<Rendezvous> RendezvousByDate(LocalDate dateRv) {
        return rendezvousRepository.findRendezvousByDateRv(dateRv);
    }

    public Rendezvous AddPatientToRendezVous(Patient patient, Rendezvous rendezvous){
        rendezvous.setPatient(patient);
        return rendezvousRepository.saveAndFlush(rendezvous);
    }

    public Rendezvous AddMedecinToPatient(Medecin medecin, Rendezvous rendezvous){
        rendezvous.setMedecin(medecin);
        return rendezvousRepository.saveAndFlush(rendezvous);
    }

    public Rendezvous getRendezvousByPatient(Patient patient){
        return rendezvousRepository.findRendezvousByPatient(patient);
    }

    public Rendezvous AddConsultationToRendezVous(Consultation consultation, Rendezvous rendezvous){
        rendezvous.setConsultation(consultation);
        return rendezvousRepository.saveAndFlush(rendezvous);
    }

    public List<Rendezvous> getAllRendezvousByMedecin(Medecin medecin){
        return rendezvousRepository.findRendezvousByMedecin(medecin);
    }
}
