package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.dao.PersonneRepository;
import com.mbc.clickclinic.dao.SecretaireRepository;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MedecinImple implements MedecinService{

    private final MedecinRepository medecinRepository;
    private final SecretaireRepository secretaireRepository;
    private final PatientService patientService;
    private final PersonneRepository personneRepository;
    private final RendezvousService rendezvousService;

    @Autowired
    public MedecinImple(MedecinRepository medecinRepository, @Lazy PatientService patientService, SecretaireRepository secretaireRepository, PersonneRepository personneRepository,@Lazy RendezvousService rendezvousService){
        this.medecinRepository = medecinRepository;
        this.patientService = patientService;
        this.personneRepository = personneRepository;
        this.secretaireRepository = secretaireRepository;
        this.rendezvousService = rendezvousService;
    }


    public Medecin getMedecinByEmail(String email){
        return medecinRepository.findMedecinByEmail(email);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        medecin.setRole(GeneralRole.MEDECIN.getRole());
        return medecinRepository.saveAndFlush(medecin);
    }


    public Medecin saveMedecin(Medecin medecin, Model model){
        if(medecinRepository.findMedecinByEmail(medecin.getEmail()) != null || personneRepository.findPersonneByEmail(medecin.getEmail()) != null ||
                patientService.getPatientByEmail(medecin.getEmail()) != null || secretaireRepository.findSecretaireByEmail(medecin.getEmail()) != null){
            model.addAttribute("emailMedecinExist", "Email exits !");
            return null;
        }
        if(medecin.getNom().matches(".*[0-9].*") || medecin.getPrenom().matches(".*[0-9].*")){
            model.addAttribute("numbers", "Nom ou prénom contient un nombre !");
            return null;
        }
        return saveMedecin(medecin);
    }

    public List<Medecin> getMedecinBySpecialty(String specialite){
        return medecinRepository.findMedecinsBySpecialite(specialite);
    }

    @Override
    public void deleteMedecin(Medecin medecin) {
        List<Rendezvous> rendezvousList = medecin.getRendezvousList();
        if(rendezvousList != null || rendezvousList.size() != 0){
            for(Rendezvous rendezvous : rendezvousList){
                rendezvousService.deleteRendezvous(rendezvous);
            }
        }
        medecinRepository.delete(medecin);
    }


    @Override
    public Medecin findMedecinByNom(String mName) {
        return medecinRepository.findMedecinByNom(mName);
    }

    @Override
    public List<Medecin> medecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin medecinById(Integer id) {
        return medecinRepository.findById(id).get();
    }
}
