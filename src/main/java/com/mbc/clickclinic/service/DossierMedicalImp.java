package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.DossierMedicalRepository;
import com.mbc.clickclinic.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DossierMedicalImp implements DossierMedicalService{

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private RendezvousService rendezvousService;

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PatientService patientService;

    @Override
    public DossierMedicale create(DossierMedicale dossierMedicale) {

        return dossierMedicalRepository.save(dossierMedicale);
    }

    @Override
    public List<DossierMedicale> getAllDossiersByMedecin(Medecin medecin) {

        List<DossierMedicale> dossiers = new ArrayList<>();
        List<Rendezvous> rendezvousList = rendezvousService.getAllRendezvousByMedecin(medecin);
        for(Rendezvous rendezvous : rendezvousList){
            DossierMedicale dossier = dossierMedicalRepository.getDossierMedicaleByPatient(rendezvous.getPatient());
            if(dossier != null && !(dossiers.contains(dossier))){
                dossiers.add(dossier);
            }
        }
        return dossiers;
    }

    @Override
    public DossierMedicale getDossierById(int idDossier) {
        return dossierMedicalRepository.findById(idDossier).get();
    }

    @Override
    public DossierMedicale updateDossier(DossierMedicale dossierMedicale) {
        return dossierMedicalRepository.save(dossierMedicale);
    }

    @Override
    public void deleteDossier(DossierMedicale dossierMedicale) {
        List<Consultation> consultations = dossierMedicale.getConsultationList();
        if(consultations.size() != 0 || consultations != null){
            for (Consultation consultation : consultations){
                consultationService.deleteConsultation(consultation);
            }
        }
        Patient patient = dossierMedicale.getPatient();
        patient.setDossierMedicale(null);
        dossierMedicale.setPatient(null);
        patientService.savePatient(patient);
        dossierMedicalRepository.delete(dossierMedicale);

    }
}
