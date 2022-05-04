package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.DossierMedicale;

import java.util.List;

public interface DossierMedicalService {

    public DossierMedicale create(DossierMedicale dossierMedicale);
    public List<DossierMedicale> getAllDossiersByMedecin(int idMedecin);

}
