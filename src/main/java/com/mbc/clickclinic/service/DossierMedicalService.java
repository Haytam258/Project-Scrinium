package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.DossierMedicale;
import com.mbc.clickclinic.entities.Medecin;

import java.util.List;

public interface DossierMedicalService {

    public DossierMedicale create(DossierMedicale dossierMedicale);
    public List<DossierMedicale> getAllDossiersByMedecin(Medecin medecin);
    public DossierMedicale getDossierById(int idDossier);
    public DossierMedicale updateDossier(DossierMedicale dossierMedicale);
    public void deleteDossier(DossierMedicale dossierMedicale);

}
