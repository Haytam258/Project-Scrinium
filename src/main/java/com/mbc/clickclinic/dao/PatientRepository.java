package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    public Patient findPatientByNom(String nom);
   // public List<Patient> findPatientByDateCreation(LocalDate date);
}
