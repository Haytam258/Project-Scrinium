package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PatientRepository;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientImple implements PatientService{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientImple(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient savePatient(Patient patient) {
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
    public Patient PatientById(Long id) {
        return patientRepository.findById(Math.toIntExact(id)).get();
    }
}
