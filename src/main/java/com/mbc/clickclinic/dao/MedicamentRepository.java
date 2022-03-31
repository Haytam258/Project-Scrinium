package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
}
