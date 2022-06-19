package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeCertificatRepository extends JpaRepository<DemandeCertificat, Integer> {

    DemandeCertificat findDemandeCertificatByPatient(Patient patient);
    List<DemandeCertificat> findDemandeCertificatsByMedecin(Medecin medecin);
    DemandeCertificat findDemandeCertificatByPatientAndMedecinAndStatus(Patient patient, Medecin medecin, Integer status);
    DemandeCertificat findDemandeCertificatByPatientAndStatus(Patient patient, Integer status);

}
