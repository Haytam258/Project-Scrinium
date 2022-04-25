package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Integer> {
}
