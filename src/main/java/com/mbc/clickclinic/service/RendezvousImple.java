package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.RendezvousRepository;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Rendezvous RendezvousByDate(Date dateRv) {
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
}
