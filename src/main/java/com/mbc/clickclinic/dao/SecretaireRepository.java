package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaireRepository extends JpaRepository<Secretaire,Integer> {

    public Secretaire findSecretaireByNom(String nom);
}
