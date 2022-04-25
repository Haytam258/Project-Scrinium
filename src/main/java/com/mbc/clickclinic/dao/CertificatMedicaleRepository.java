package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.CertificatMedicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificatMedicaleRepository extends JpaRepository<CertificatMedicale,Integer> {

}