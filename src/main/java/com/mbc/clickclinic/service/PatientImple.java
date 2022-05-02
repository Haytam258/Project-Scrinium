package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        return patientRepository.saveAndFlush(patient);
    }

    public List<Patient> getPatientsOfToday(LocalDate date){
        List<Patient> patientList = new ArrayList<>();
        List<Rendezvous> rendezvousList = rendezvousService.RendezvousByDate(date);
        if(rendezvousList.size() != 0){
            for(Rendezvous rendezvous : rendezvousList){
                patientList.add(rendezvous.getPatient());
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
