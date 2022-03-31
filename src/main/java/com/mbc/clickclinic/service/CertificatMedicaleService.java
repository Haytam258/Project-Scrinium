package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.CertificatMedicale;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CertificatMedicaleService {
    //basic
    CertificatMedicale saveCertificatMedical(CertificatMedicale certificatMedical);
    void deleteCertificatMedical(CertificatMedicale certificatMedical);
    CertificatMedicale updateCertificatMedical(CertificatMedicale certificatMedical);
    List<CertificatMedicale> CertificatMedicals();
    CertificatMedicale CertificatMedicalById(Long id);
    //fon
    CertificatMedicale CertificatMedicalOfPatient(Long idPatient,String nomPatient);
    CertificatMedicale CertificatMedicalOfPatientInDate(Date date);

}
