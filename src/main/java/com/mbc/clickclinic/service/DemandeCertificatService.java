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
}
