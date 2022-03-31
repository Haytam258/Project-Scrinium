package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Long> {
}
