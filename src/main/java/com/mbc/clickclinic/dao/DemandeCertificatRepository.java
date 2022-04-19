package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeCertificatRepository extends JpaRepository<DemandeCertificat, Integer> {

    public DemandeCertificat findDemandeCertificatByPatient(Patient patient);
}
