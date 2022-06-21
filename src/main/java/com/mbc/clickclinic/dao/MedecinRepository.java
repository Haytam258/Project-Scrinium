package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

    public Medecin findMedecinByNom(String nom);
    public List<Medecin> findMedecinsBySpecialite(String specialite);
    Medecin findMedecinByEmail(String email);
}
