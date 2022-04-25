package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.SalleDattente;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SalleDattenteService {
    SalleDattente saveSalleDattente(SalleDattente notification);
    void deleteSalleDattente(SalleDattente notification);
    public List<SalleDattente> getSalles();
    public SalleDattente getSalleById(int id);
    public SalleDattente addPatientToSalle(SalleDattente salleDattente, Patient patient);
    public void deletePatientFromSalle(SalleDattente salleDattente, Patient patient);
}
