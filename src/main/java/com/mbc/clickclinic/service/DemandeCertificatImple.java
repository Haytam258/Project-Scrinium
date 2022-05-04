package com.mbc.clickclinic.service;


import com.mbc.clickclinic.dao.DemandeCertificatRepository;
import com.mbc.clickclinic.dao.TypeCertificationRepository;
import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.TypeCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeCertificatImple implements DemandeCertificatService {

    private final DemandeCertificatRepository demandeCertificatRepository;
    private final PatientService patientService;
    private final TypeCertificationRepository typeCertificationRepository;

    @Autowired
    public DemandeCertificatImple(DemandeCertificatRepository demandeCertificatRepository, PatientService patientService, TypeCertificationRepository typeCertificationRepository){
        this.demandeCertificatRepository = demandeCertificatRepository;
        this.patientService = patientService;
        this.typeCertificationRepository = typeCertificationRepository;
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

    public DemandeCertificat getDemandesByPatient(Patient patient){
        return demandeCertificatRepository.findDemandeCertificatByPatient(patient);
    }

    public DemandeCertificat getDemandeByPatientAndMedecin(Patient patient, Medecin medecin){
        return demandeCertificatRepository.findDemandeCertificatByPatientAndMedecin(patient,medecin);
    }
}
