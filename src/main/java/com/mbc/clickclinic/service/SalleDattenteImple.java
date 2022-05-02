package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.SalleDattentRepository;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.SalleDattente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleDattenteImple implements SalleDattenteService{

    private final SalleDattentRepository salleDattentRepository;

    private final PatientService patientService;

    @Autowired
    public SalleDattenteImple(SalleDattentRepository salleDattentRepository, PatientService patientService){
        this.salleDattentRepository = salleDattentRepository;
        this.patientService = patientService;
    }
    @Override
    public SalleDattente saveSalleDattente(SalleDattente salleDattente) {
        return salleDattentRepository.saveAndFlush(salleDattente);
    }

    @Override
    public void deleteSalleDattente(SalleDattente salleDattente) {
        salleDattentRepository.delete(salleDattente);
    }

    public List<SalleDattente> getSalles(){
        return salleDattentRepository.findAll();
    }

    public SalleDattente getSalleById(int id){
        return salleDattentRepository.findById(id).get();
    }

    public SalleDattente addPatientToSalle(SalleDattente salleDattente, Patient patient){
        if(salleDattente.getPatientList().contains(patient)){
            return null;
        }
        else {
            salleDattente.getPatientList().add(patient);
            patient.setSalleDattente(salleDattente);
        }
        patientService.savePatient(patient);
        return salleDattentRepository.saveAndFlush(salleDattente);
    }

    public void deletePatientFromSalle(SalleDattente salleDattente, Patient patient){
        if(salleDattente.getPatientList().contains(patient)){
            salleDattente.getPatientList().remove(patient);
            patient.setSalleDattente(null);
        }
        patientService.savePatient(patient);
        salleDattentRepository.saveAndFlush(salleDattente);
    }

}
