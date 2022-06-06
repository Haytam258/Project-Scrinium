package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PatientImple(PatientRepository patientRepository, RendezvousService rendezvousService){
        this.patientRepository = patientRepository;
        this.rendezvousService = rendezvousService;
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

    public Patient savePatient(Patient patient, Model model){
        if(patientRepository.findPatientByEmail(patient.getEmail()) != null){
            model.addAttribute("emailExist", "Email exists !");
            return null;
        }
        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public void deletePatient(Patient patient) {
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
