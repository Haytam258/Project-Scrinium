package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.TypeCertification;

import java.util.List;

public interface DemandeCertificatService {
    public DemandeCertificat saveDemandeCertificat(DemandeCertificat demandeCertificat);
    public DemandeCertificat addPatientToCertificat(DemandeCertificat demandeCertificat, Patient patient);
    public DemandeCertificat addMedecinToCertificat(DemandeCertificat demandeCertificat, Medecin medecin);
    public void deleteDemandeCertificat(DemandeCertificat demandeCertificat);
    public DemandeCertificat getDemandeCertificatById(int id);
    List<TypeCertification> getTypesCertificat();
    TypeCertification saveTypeCertification(TypeCertification typeCertification);
    List<DemandeCertificat> getDemandesByMedecin(Medecin medecin);
    DemandeCertificat getDemandesByPatient(Patient patient);
    List<DemandeCertificat> getDemandesCertificats();
    DemandeCertificat getDemandeByPatientAndMedecinAndStatus(Patient patient, Medecin medecin, Integer Status);
    List<DemandeCertificat> getUntreatedDemandes();
    public DemandeCertificat getPatientUntreatedDemande(Patient patient);
    public List<Patient> getPatientsWithDemandeForMedecin(Medecin medecin);
    public void deleteDemande(DemandeCertificat demandeCertificat);
    DemandeCertificat getDemandeByPatientAndStatus(Patient patient, Integer status);
}
