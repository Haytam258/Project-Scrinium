package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

    @Query(value = "select * from medecin  where nom = :nom", nativeQuery = true)
    public Medecin findMedecinByNom(String nom);

    Medecin findMedecinByEmail(String email);
}
