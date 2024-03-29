package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public interface RendezvousService {
    public Rendezvous saveRendezvous(Rendezvous rendezvous, RedirectAttributes redirectAttributes);
    Rendezvous saveRendezvous(Rendezvous rendezvous, Model model);
    void deleteRendezvous(Rendezvous rendezvous);
    Rendezvous updateRendezvous(Rendezvous rendezvous);
    List<Rendezvous> Rendezvouss();
    public Rendezvous RendezvousById(int id);
    //Remplacer LocalDateTime par LocalDate and all is good !
    public List<Rendezvous> RendezvousByDate(LocalDate dateRv);
    public Rendezvous AddPatientToRendezVous(Patient patient, Rendezvous rendezvous);
    public Rendezvous AddMedecinToPatient(Medecin medecin, Rendezvous rendezvous);
    public List<Rendezvous> getRendezvousByPatient(Patient patient);
    public Rendezvous AddConsultationToRendezVous(Consultation consultation, Rendezvous rendezvous);
    List<Rendezvous> rendezvousByDate(LocalDate date);
    List<Rendezvous> getAllRendezvousByMedecin(Medecin medecin);
    List<Rendezvous> rendezvousByDateAndStatut(LocalDate date, Integer statut);
    List<Rendezvous> rendezvousByStatut(Integer statut);
    public List<Rendezvous> getDemandesRendezByPatient(Patient patient);
    public List<Rendezvous> getRendezvousHistoriqueOfPatient(Patient patient);

}
