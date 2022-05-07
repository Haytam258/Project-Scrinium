package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.DossierMedicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Integer> {

    List<Consultation> findConsultationsByDossierMedicale(DossierMedicale dossierMedicale);
}
