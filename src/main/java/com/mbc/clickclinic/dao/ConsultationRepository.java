package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
