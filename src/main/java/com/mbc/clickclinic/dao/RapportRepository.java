package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepository extends JpaRepository<Rapport,Long> {
}
