package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.TypeMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMedicamentRepository extends JpaRepository<TypeMedicament, Integer> {
}
