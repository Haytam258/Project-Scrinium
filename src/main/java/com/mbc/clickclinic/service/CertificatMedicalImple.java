package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.CertificatMedicaleRepository;
import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.entities.CertificatMedicale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CertificatMedicalImple implements CertificatMedicaleService {

    private final CertificatMedicaleRepository certifRepo;

    private final PatientRepository patientRepository;

    @Autowired
    public CertificatMedicalImple(CertificatMedicaleRepository certifRepo, PatientRepository patientRepository){
        this.certifRepo = certifRepo;
        this.patientRepository = patientRepository;
    }

    @Override
    public CertificatMedicale saveCertificatMedical(CertificatMedicale certificatMedical) {
        return certifRepo.saveAndFlush(certificatMedical);
    }

    @Override
    public void deleteCertificatMedical(CertificatMedicale certificatMedical) {
        certifRepo.delete(certificatMedical);
    }

    //Reste à déterminer quoi et comment faire le update ! Cela en déterminant la structure de CertificatMédicale dans diag de classe.
    @Override
    public CertificatMedicale updateCertificatMedical(CertificatMedicale certificatMedical) {
        return null;
    }

    @Override
    public List<CertificatMedicale> CertificatMedicals() {
        return certifRepo.findAll();
    }

    @Override
    public CertificatMedicale CertificatMedicalById(int id) {
        return certifRepo.findById(id).get();
    }

    //Il faut savoir la structure de la base de données et donc la structure des entities avant de faire cette fonction
    @Override
    public CertificatMedicale CertificatMedicalOfPatient(int idPatient, String nomPatient) {
        return null;
    }

    //De meme pour cette fonction
    @Override
    public CertificatMedicale CertificatMedicalOfPatientInDate(Date date) {
        return null;
    }

    public List<CertificatMedicale> getCertificats(){
        return certifRepo.findAll();
    }
}
