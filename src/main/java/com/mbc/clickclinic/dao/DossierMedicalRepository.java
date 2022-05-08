package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.DossierMedicale;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedicale, Integer> {

    DossierMedicale getDossierMedicaleByPatient(Patient patient);

}
