package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.RendezvousRepository;
import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class RendezvousImple implements RendezvousService{

    private final RendezvousRepository rendezvousRepository;
    private final PatientService patientService;
    private final MedecinService medecinService;

    @Autowired
    public RendezvousImple(RendezvousRepository rendezvousRepository, PatientService patientService, MedecinService medecinService){
        this.rendezvousRepository = rendezvousRepository;
        this.medecinService = medecinService;
        this.patientService = patientService;
    }
    @Override
    public Rendezvous saveRendezvous(Rendezvous rendezvous) {
        List<Rendezvous> rend = rendezvousRepository.findRendezvousByDateRv(rendezvous.getDateRv());
        if(rend.size() != 0){
            for (Iterator<Rendezvous> rendezvousIterator = rend.iterator(); rendezvousIterator.hasNext();) {
                Rendezvous rendez = rendezvousIterator.next();
                //On supposera qu'un rendezvous passera 1 heure maximum, donc on vérifie que la date est libre avant de l'insérer. Le statut 0 indique que le rendezvous n'est pas
                //encore fait.
                if(rendezvous.getHeure() <= rendez.getHeure() + 1 && rendezvous.getHeure() >= rendez.getHeure() - 1 && rendez.getStatut() == 0 && rendezvous.getStatut() == 0){
                    return null;
                }
                else {
                    return rendezvousRepository.saveAndFlush(rendezvous);
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
        return null;
    }

    @Override
    public List<Rendezvous> Rendezvouss() {
        return rendezvousRepository.findAll();
    }

    @Override
    public Rendezvous RendezvousById(Long id) {
        return rendezvousRepository.findById(id).get();
    }

    @Override
    public List<Rendezvous> RendezvousByDate(LocalDateTime dateRv) {
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
}
