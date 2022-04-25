package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Integer> {

    public Medicament findMedicamentByLibelle(String libelle);
    public Medicament findMedicamentByCodeATC(String codeATC);
}
