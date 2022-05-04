package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.DossierMedicalRepository;
import com.mbc.clickclinic.entities.DossierMedicale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DossierMedicalImp implements DossierMedicalService{

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Override
    public DossierMedicale create(DossierMedicale dossierMedicale) {

        return dossierMedicalRepository.save(dossierMedicale);
    }

    @Override
    public List<DossierMedicale> getAllDossiersByMedecin(int idMedecin) {

        List<DossierMedicale> dossiers = new ArrayList<>();
        List<Integer> patients = dossierMedicalRepository.getAllPatientsByMedecin(idMedecin);
        for(int patient : patients){
            DossierMedicale dossier = dossierMedicalRepository.getDossierByPatient(patient);
            dossiers.add(dossier);
        }
        return dossiers;
    }
}
