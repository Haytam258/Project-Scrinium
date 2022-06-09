package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;


public interface PatientService {
    Patient savePatient(Patient patient);
    void deletePatient(Patient patient);
    Patient updatePatient(Patient patient);
    Patient findPatientBykw(String kw);
    Patient findPatientByNom(String pName);
    List<Patient> patients();
    public Patient PatientById(int id);
    public Patient savePatient(Patient patient, Model model);
    List<Patient> getPatientsOfToday(LocalDate date);
    public String[] patientsEmail();
}
