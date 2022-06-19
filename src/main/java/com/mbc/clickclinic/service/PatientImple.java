package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.dao.PersonneRepository;
import com.mbc.clickclinic.entities.*;
import com.mbc.clickclinic.security.GeneralRole;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientImple implements PatientService{

    private final PatientRepository patientRepository;
    private final RendezvousService rendezvousService;
    private final MedecinService medecinService;
    private final PersonneRepository personneRepository;
    private final SecretaireService secretaireService;
    private final DossierMedicalService dossierM;

    @Autowired
    public PatientImple(PatientRepository patientRepository, RendezvousService rendezvousService, @Lazy MedecinService medecinService, PersonneRepository personneRepository, @Lazy SecretaireService secretaireService, @Lazy DossierMedicalService dossierM){
        this.patientRepository = patientRepository;
        this.rendezvousService = rendezvousService;
        this.secretaireService = secretaireService;
        this.personneRepository = personneRepository;
        this.medecinService = medecinService;
        this.dossierM = dossierM;
    }

    public Patient getPatientByEmail(String email){
        return patientRepository.findPatientByEmail(email);
    }

    @Override
    public Patient savePatient(Patient patient) {
        /*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));*/
        patient.setRole(GeneralRole.PATIENT.getRole());
        return patientRepository.saveAndFlush(patient);
    }

    public List<Patient> getPatientsOfToday(LocalDate date){
        List<Patient> patientList = new ArrayList<>();
        List<Rendezvous> rendezvousList = rendezvousService.RendezvousByDate(date);
        if(rendezvousList.size() != 0){
            for(Rendezvous rendezvous : rendezvousList){
                if(patientList.contains(rendezvous.getPatient())){

                }
                else {
                    patientList.add(rendezvous.getPatient());
                }
            }
        }
        return patientList;

    }

    public String[] patientsEmail(){
        List<String> emailList = new ArrayList<>();
        List<Patient> patientList = patientRepository.findAll();
        for(Patient patient : patientList){
            if(EmailValidator.getInstance().isValid(patient.getEmail())){
                emailList.add(patient.getEmail());
            }
        }
        return emailList.toArray(new String[0]);
    }

    public Patient savePatient(Patient patient, Model model){
        if(medecinService.getMedecinByEmail(patient.getEmail()) != null || personneRepository.findPersonneByEmail(patient.getEmail()) != null ||
                patientRepository.findPatientByEmail(patient.getEmail()) != null || secretaireService.findSecretaireByEmail(patient.getEmail()) != null){
            model.addAttribute("emailExist", "Email exists !");
            return null;
        }
        if(patient.getNom().matches(".*[0-9].*") || patient.getPrenom().matches(".*[0-9].*")){
            model.addAttribute("numbers", "Nom ou prénom contient un nombre !");
            return null;
        }
        if(!EmailValidator.getInstance().isValid(patient.getEmail())){
            model.addAttribute("emailInvalid", "Email est invalide, Vérifiez votre saisie !");
            return null;
        }
        return savePatient(patient);
    }

    @Override
    public void deletePatient(Patient patient) {
        DossierMedicale dossierMedicale = patient.getDossierMedicale();
        List<Rendezvous> rendezvousList = patient.getRendezvousList();
        if(rendezvousList != null || rendezvousList.size() != 0){
            for(Rendezvous rendezvous : rendezvousList){
                rendezvousService.deleteRendezvous(rendezvous);
            }
        }
        dossierM.deleteDossier(dossierMedicale);
        patient.setSalleDattente(null);
        patient.setDossierMedicale(null);
        patientRepository.delete(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    @Override
    public Patient findPatientBykw(String kw) {
        return null;
    }

    @Override
    public Patient findPatientByNom(String pName) {
        return patientRepository.findPatientByNom(pName);
    }

    @Override
    public List<Patient> patients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient PatientById(int id) {
        return patientRepository.findById(id).get();
    }
}
