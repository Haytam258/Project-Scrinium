package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.CategorieMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieMedicamentRepository extends JpaRepository<CategorieMedicament, Integer> {
}
