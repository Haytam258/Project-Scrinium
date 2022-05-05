package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.DossierMedicalRepository;
import com.mbc.clickclinic.entities.DossierMedicale;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DossierMedicalImp implements DossierMedicalService{

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private RendezvousService rendezvousService;

    @Override
    public DossierMedicale create(DossierMedicale dossierMedicale) {

        return dossierMedicalRepository.save(dossierMedicale);
    }

    @Override
    public List<DossierMedicale> getAllDossiersByMedecin(Medecin medecin) {

        List<DossierMedicale> dossiers = new ArrayList<>();
        List<Rendezvous> rendezvousList = rendezvousService.getAllRendezvousByMedecin(medecin);
        for(Rendezvous rendezvous : rendezvousList){
            DossierMedicale dossier = dossierMedicalRepository.getDossierMedicaleByPatient(rendezvous.getPatient());
            if(dossier != null && !(dossiers.contains(dossier))){
                dossiers.add(dossier);
            }
        }
        return dossiers;
    }
}
