package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public interface RendezvousService {
    Rendezvous saveRendezvous(Rendezvous rendezvous);
    void deleteRendezvous(Rendezvous rendezvous);
    Rendezvous updateRendezvous(Rendezvous rendezvous);
    List<Rendezvous> Rendezvouss();
    Rendezvous RendezvousById(Long id);
    public List<Rendezvous> RendezvousByDate(LocalDateTime dateRv);
    public Rendezvous AddPatientToRendezVous(Patient patient, Rendezvous rendezvous);
    public Rendezvous AddMedecinToPatient(Medecin medecin, Rendezvous rendezvous);
    public Rendezvous getRendezvousByPatient(Patient patient);
    public Rendezvous AddConsultationToRendezVous(Consultation consultation, Rendezvous rendezvous);

}
