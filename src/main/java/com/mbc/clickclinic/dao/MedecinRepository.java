package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

    public Medecin findMedecinByNom(String nom);
    Medecin findMedecinByEmail(String email);
}
