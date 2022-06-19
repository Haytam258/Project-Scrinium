package com.mbc.clickclinic.service;


import com.mbc.clickclinic.dao.DemandeCertificatRepository;
import com.mbc.clickclinic.dao.TypeCertificationRepository;
import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.TypeCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeCertificatImple implements DemandeCertificatService {

    private final DemandeCertificatRepository demandeCertificatRepository;
    private final PatientService patientService;
    private final TypeCertificationRepository typeCertificationRepository;
    private final MedecinService medecinService;

    @Autowired
    public DemandeCertificatImple(DemandeCertificatRepository demandeCertificatRepository, PatientService patientService, TypeCertificationRepository typeCertificationRepository, @Lazy MedecinService medecinService){
        this.demandeCertificatRepository = demandeCertificatRepository;
        this.patientService = patientService;
        this.typeCertificationRepository = typeCertificationRepository;
        this.medecinService = medecinService;
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

    public List<TypeCertification> getTypesCertificat(){
        return typeCertificationRepository.findAll();
    }

    public TypeCertification saveTypeCertification(TypeCertification typeCertification){
        return typeCertificationRepository.saveAndFlush(typeCertification);
    }

    public List<DemandeCertificat> getDemandesCertificats(){
        return demandeCertificatRepository.findAll();
    }

    public List<DemandeCertificat> getDemandesByMedecin(Medecin medecin){
        return demandeCertificatRepository.findDemandeCertificatsByMedecin(medecin);
    }

    public List<DemandeCertificat> getUntreatedDemandes(){
        List<DemandeCertificat> demandeUntreatedList = new ArrayList<>();
        for(DemandeCertificat demandeCertificat : demandeCertificatRepository.findAll()){
            if(demandeCertificat.getStatus() == 0){
                demandeUntreatedList.add(demandeCertificat);
            }
        }
        return demandeUntreatedList;
    }

    public List<Patient> getPatientsWithDemandeForMedecin(Medecin medecin){
        List<Patient> patientList = new ArrayList<>();
        for(DemandeCertificat demandeCertificat : demandeCertificatRepository.findDemandeCertificatsByMedecin(medecin)){
            if(demandeCertificat.getStatus() == 0){
                patientList.add(demandeCertificat.getPatient());
            }
        }
        return patientList;
    }

    public void deleteDemande(DemandeCertificat demandeCertificat){
        Patient patient = demandeCertificat.getPatient();
        Medecin medecin = demandeCertificat.getMedecin();
        demandeCertificat.setTypeCertification(null);
        demandeCertificat.setMedecin(null);
        demandeCertificat.setPatient(null);
        patient.setDemandeCertificat(null);
        medecin.remove(demandeCertificat);
        patientService.savePatient(patient);
        demandeCertificatRepository.delete(demandeCertificat);
        medecinService.saveMedecin(medecin);
    }

    @Override
    public DemandeCertificat getDemandeByPatientAndStatus(Patient patient, Integer status) {
        return demandeCertificatRepository.findDemandeCertificatByPatientAndStatus(patient,status);
    }

    public DemandeCertificat getDemandesByPatient(Patient patient){
        return demandeCertificatRepository.findDemandeCertificatByPatient(patient);
    }

    public DemandeCertificat getPatientUntreatedDemande(Patient patient){
        return demandeCertificatRepository.findDemandeCertificatByPatientAndStatus(patient, 0);
    }

    public DemandeCertificat getDemandeByPatientAndMedecinAndStatus(Patient patient, Medecin medecin, Integer status){
        return demandeCertificatRepository.findDemandeCertificatByPatientAndMedecinAndStatus(patient,medecin,status);
    }
}
