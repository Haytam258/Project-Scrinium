package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.CertificatMedicale;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificatMedicaleRepository extends JpaRepository<CertificatMedicale,Integer> {

    List<CertificatMedicale> findCertificatMedicalesByPatient(Patient patient);

}