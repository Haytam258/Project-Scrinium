package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.DossierMedicale;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedicale, Integer> {


    @Query(value = "select patient_id from rendezvous  where medecin_id = :idMedecin", nativeQuery = true)
    public List<Integer> getAllPatientsByMedecin(int idMedecin);

    @Query(value = "select * from dossier_medicale  where patient_id = :idPatient", nativeQuery = true)
    public DossierMedicale getDossierByPatient(int idPatient);

    DossierMedicale getDossierMedicaleByPatient(Patient patient);

}
