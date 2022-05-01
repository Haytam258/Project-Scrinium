package com.mbc.clickclinic.service;


import com.mbc.clickclinic.dao.DemandeCertificatRepository;
import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeCertificatImple implements DemandeCertificatService {

    private final DemandeCertificatRepository demandeCertificatRepository;
    private final PatientService patientService;

    @Autowired
    public DemandeCertificatImple(DemandeCertificatRepository demandeCertificatRepository, PatientService patientService){
        this.demandeCertificatRepository = demandeCertificatRepository;
        this.patientService = patientService;
    }

    public DemandeCertificat saveDemandeCertificat(DemandeCertificat demandeCertificat){
        return demandeCertificatRepository.saveAndFlush(demandeCertificat);
    }

    public DemandeCertificat addPatientToCertificat(DemandeCertificat demandeCertificat, Patient patient){
        demandeCertificat.setPatient(patient);
        patient.setDemandeCertificat(demandeCertificat);
        patientService.savePatient(patient);
        return demandeCertificatRepository.saveAndFlush(demandeCertificat);
    }

    public DemandeCertificat addMedecinToCertificat(DemandeCertificat demandeCertificat, Medecin medecin){
        demandeCertificat.setMedecin(medecin);
        return demandeCertificatRepository.saveAndFlush(demandeCertificat);
    }

    public void deleteDemandeCertificat(DemandeCertificat demandeCertificat){
        demandeCertificatRepository.delete(demandeCertificat);
    }

    public DemandeCertificat getDemandeCertificatById(int id){
        return demandeCertificatRepository.findById(id).get();
    }
}