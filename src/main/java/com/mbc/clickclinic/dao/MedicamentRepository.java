package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Long> {

    public Medicament findMedicamentByNom(String nom);
    public Medicament findMedicamentByCodeATC(String codeATC);
}
